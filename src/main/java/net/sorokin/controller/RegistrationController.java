package net.sorokin.controller;

import net.sorokin.dao.UserDao;
import net.sorokin.dao.UserDaoJdbcImpl;
import net.sorokin.entity.User;
import net.sorokin.utils.CheckUtil;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegistrationController extends HttpServlet {

    private UserDao userDao = new UserDaoJdbcImpl();

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        try {
            final String FIRST_NAME = request.getParameter("firstName");
            final String SECOND_NAME = request.getParameter("secondName");
            final String BIRTH_DAY = request.getParameter("birthDay");
            final String EMAIL = request.getParameter("email");
            final String PASSWORD = request.getParameter("password");
            final String REPEAT_PASSWORD = request.getParameter("repeatPassword");
            final String TEXT_FIELD = request.getParameter("textField");

            if ((FIRST_NAME == null || "".equals(FIRST_NAME))
                    || (SECOND_NAME == null || "".equals(SECOND_NAME))
                    || (EMAIL == null || "".equals(EMAIL))
                    || (PASSWORD == null || "".equals(PASSWORD))
                    || (REPEAT_PASSWORD == null || "".equals(REPEAT_PASSWORD))) {
                errorRegistration(request, response, "Sorry, you enter empty fild. Please, try again");
            } else {
                if (PASSWORD.equals(REPEAT_PASSWORD)) {
                    if (new CheckUtil().checkPassword(PASSWORD)) {
                        if (new CheckUtil().checkEmail(EMAIL)) {
                            try {
                                User user = userDao.selectByEmail(EMAIL);

                                if (user == null) {
                                    final User newUser = new User();
                                    newUser.setFirstName(FIRST_NAME);
                                    newUser.setSecondName(SECOND_NAME);
                                    newUser.setBirthDay(BIRTH_DAY);
                                    newUser.setEmail(EMAIL.toLowerCase());
                                    newUser.setPassword(PASSWORD);
                                    newUser.setTextField(TEXT_FIELD);

                                    int result = userDao.insert(newUser);

                                    if (result == 1) {
                                        request.getSession().setAttribute("user", newUser);
                                        request.getRequestDispatcher("privateAccount.jsp").forward(request, response);
                                        return;
                                    } else {
                                        errorRegistration(request, response, "Sorry, it's a database error");
                                    }
                                } else {
                                    errorRegistration(request, response, "Sorry, this email already exists. Please, try again");
                                }
                            } catch (Exception e) {
                                request.getRequestDispatcher("login.jsp").forward(request,response);
                            }
                        } else {
                            errorRegistration(request, response, "Sorry, you email don't validate");
                        }
                    } else {
                        errorRegistration(request, response, "Sorry, password must contains lowercase,uppercase characters and numbers.");
                    }
                } else {
                    errorRegistration(request, response, "Sorry, password must be equals repeat password. Please, try again");
                }
            }
        } catch (Exception e) {
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }
    }

    private static void errorRegistration(HttpServletRequest request, HttpServletResponse response, String text) throws ServletException, IOException {
        request.setAttribute("errorRegistered", text);
        request.getRequestDispatcher("registration.jsp").forward(request,response);
    }
}
