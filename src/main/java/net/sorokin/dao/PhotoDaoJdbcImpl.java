package net.sorokin.dao;


import net.sorokin.entity.Photo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhotoDaoJdbcImpl implements PhotoDao{


    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:~/test";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    private static final String SELECT_BY_EMAIL = "SELECT id, name, image FROM photo WHERE email = ?";
    private static final String INSERT_SQL = "INSERT INTO photo(email, name, image) values(?,?,?)";
    private static final String SELECT_IMAGE_BY_ID = "SELECT image FROM photo WHERE id = ?";
    /*private static final String CREATE_TABLE = "CREATE table photo(id integer(10) AUTO_INCREMENT," +
                                               "email varchar(50) NOT NULL," +
                                               "name varchar(255),"+
                                               "image BLOB NOT NULL)";*/


    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public List<Photo> selectPhotoByEmail(String email) throws SQLException, IOException {
        List<Photo> photos = new ArrayList<Photo>();
        byte [] array = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL);
            preparedStatement.setString(1, email);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Photo photo = new Photo();
                photo.setEmail(email);
                photo.setId(rs.getInt("id"));
                photo.setName(rs.getString("name"));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1];
                int n = 0;
                InputStream in = rs.getBinaryStream(3);
                while ((n=in.read(buffer))>=0) {
                    baos.write(buffer, 0, n);
                }
                in.close();
                array = baos.toByteArray();
                photo.setImage(array);
                photos.add(photo);
            }
        }finally {
            rs.close();
            preparedStatement.close();
            connection.close();
        }
        return photos;
    }

    public byte[] selectImageById(int id) throws SQLException {
        byte [] array = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECT_IMAGE_BY_ID);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1];
                int n = 0;
                InputStream in = rs.getBinaryStream(1);
                while ((n=in.read(buffer))>=0) {
                    baos.write(buffer, 0, n);
                }
                in.close();
                array = baos.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            rs.close();
            preparedStatement.close();
            connection.close();
        }
        return array;
    }

    public int insertPhoto(Photo photo) throws SQLException {
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(INSERT_SQL);
            preparedStatement.setString(1, photo.getEmail());
            preparedStatement.setString(2,photo.getName());
            ByteArrayInputStream bout = new ByteArrayInputStream(photo.getImage());
            preparedStatement.setBinaryStream(3, bout, (int) photo.getImage().length);
            result = preparedStatement.executeUpdate();
        }finally {
            preparedStatement.close();
            connection.close();
        }
        return result;
    }

    /*public void createTable() throws SQLException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.execute(CREATE_TABLE);
        statement.close();
        connection.close();
    }*/
}
