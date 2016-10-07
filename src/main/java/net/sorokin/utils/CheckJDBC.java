package net.sorokin.utils;


import net.sorokin.dao.PhotoDao;
import net.sorokin.dao.PhotoDaoJdbcImpl;
import net.sorokin.dao.UserDaoJdbcImpl;
import net.sorokin.entity.Photo;
import net.sorokin.entity.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CheckJDBC {
    public static void main(String[] args) throws SQLException, IOException {
        PhotoDaoJdbcImpl photoDaoJdbc = new PhotoDaoJdbcImpl();
        List<Photo> photos = photoDaoJdbc.selectPhotoByEmail("artyom_sorokin@mail.ru");

        for (Photo photo: photos) {
            selectImageByID(photo.getId());
        }


       /* userDaoJdbc.createTable();*/

        /*User user = new User();
        user.setFirstName("Artyom");
        user.setSecondName("Sorokin");
        user.setBirthDay(null);
        user.setEmail("artyom_sorokin@mail.ru");
        user.setPassword("Artyom1111");
        user.setTextField("Lala lala lala");

        userDaoJdbc.insert(user);*/



    }

    public static void selectImageByID(int id) throws IOException, SQLException {
        PhotoDao photoDao = new PhotoDaoJdbcImpl();
        byte[] buffer = photoDao.selectImageById(id);
        File image = new File("D:\\frt\\image1234567"+id+".jpg");
        FileOutputStream fos = new FileOutputStream(image);
        System.out.println();
        fos.write(buffer);
        System.out.println();
    }
}
