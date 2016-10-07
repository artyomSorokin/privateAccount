package net.sorokin.controller;


import net.sorokin.dao.PhotoDao;
import net.sorokin.dao.PhotoDaoJdbcImpl;
import net.sorokin.entity.Photo;
import net.sorokin.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AccountController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            HttpSession session = req.getSession(false);
            User user = (User) session.getAttribute("user");
            PhotoDao photoDao = new PhotoDaoJdbcImpl();
            System.out.println("doGet upload");

            List<Photo> listPhoto = null;

            listPhoto = photoDao.selectPhotoByEmail(user.getEmail());

            req.setAttribute("listPhoto", listPhoto);
            req.getRequestDispatcher("privateAccount.jsp").forward(req, resp);
            return;
        } catch (SQLException e) {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
