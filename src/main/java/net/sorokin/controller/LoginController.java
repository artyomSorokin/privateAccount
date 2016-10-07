package net.sorokin.controller;


import net.sorokin.dao.UserDao;
import net.sorokin.dao.UserDaoJdbcImpl;
import net.sorokin.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class LoginController extends HttpServlet {

    private UserDao userDao = new UserDaoJdbcImpl();


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        try {
            final String email = request.getParameter("email");
            final String password = request.getParameter("password");

            User user = userDao.selectByEmail(email);

            if (user == null) {
                request.setAttribute("errorLogin", "Sorry, this login and password don't find. Please enter correct login and password.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                    request.getSession().setAttribute("user", user);
                    request.getRequestDispatcher("/account").forward(request, response);
                } else {
                    request.setAttribute("errorLogin", "Sorry, this login and password don't find. Please enter correct login and password.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            request.getRequestDispatcher("login,jsp").forward(request, response);
        }
    }

}
