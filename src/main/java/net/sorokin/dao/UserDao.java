package net.sorokin.dao;


import net.sorokin.entity.User;

import java.sql.SQLException;

public interface UserDao {

    public User selectByEmail(String email) throws SQLException;

    public int insert(User user);
}
