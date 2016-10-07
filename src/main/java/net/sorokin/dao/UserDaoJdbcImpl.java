package net.sorokin.dao;


import net.sorokin.entity.User;
import net.sorokin.exception.NotUniqueUserEmailException;

import java.sql.*;


public class UserDaoJdbcImpl implements UserDao{

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:~/test";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    /*private static final String CREATE_TABLE = "CREATE table users(id integer(10) AUTO_INCREMENT," +
                                              " firstName varchar(20) NOT NULL,"+
                                              " secondName varchar(20) NOT NULL,"+
                                              " birthDay varchar(20),"+
                                              " email varchar(50) NOT NULL UNIQUE," +
                                              " password varchar(20) NOT NULL," +
                                              " text VARCHAR (255)," +
                                              " PRIMARY KEY(id))";*/

    private static final String SELECT_BY_EMAIL = "SELECT id, firstName, secondName, birthDay, password, text FROM users WHERE email = ?";
    private static final String INSERT_SQL = "INSERT INTO users(firstName, secondName, birthDay, email, password, text) values(?, ?, ?, ?, ?, ?)";


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

    public User selectByEmail(String email) throws SQLException {

        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL);
            preparedStatement.setString(1,email);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = new User();
                System.out.println("new User");
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("firstName"));
                user.setSecondName(rs.getString("secondName"));
                user.setBirthDay(rs.getString("birthDay"));
                user.setEmail(email);
                user.setPassword(rs.getString("password"));
                user.setTextField(rs.getString("text"));
            }
        } finally {
            rs.close();
            preparedStatement.close();
            connection.close();
        }
        return user;
    }

    public int insert(User user) {
        int result = 0;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = null;
            try {
                if (existWithEmail(connection, user.getEmail())) {
                    throw new NotUniqueUserEmailException("Email" + user.getEmail() + "Already existed");
                }
                preparedStatement = connection.prepareStatement(INSERT_SQL);
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getSecondName());
                preparedStatement.setString(3, user.getBirthDay());
                preparedStatement.setString(4, user.getEmail());
                preparedStatement.setString(5, user.getPassword());
                preparedStatement.setString(6, user.getTextField());
                result = preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                preparedStatement.close();
                connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean existWithEmail(Connection conn, String email) throws SQLException {

        PreparedStatement ps = conn.prepareStatement(SELECT_BY_EMAIL);
        ps.setString(1,email);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

   /* public void createTable() throws SQLException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.execute(CREATE_TABLE);
        statement.close();
        connection.close();
    }*/
}
