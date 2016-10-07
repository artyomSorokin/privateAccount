package net.sorokin.dao;


import net.sorokin.entity.Photo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface PhotoDao {

    public List<Photo> selectPhotoByEmail(String email) throws SQLException, IOException;

    public int insertPhoto(Photo photo) throws SQLException;

    public byte[] selectImageById(int id) throws SQLException;

}
