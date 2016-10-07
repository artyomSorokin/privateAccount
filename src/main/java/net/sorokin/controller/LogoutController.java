package net.sorokin.controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(false);
            if (session != null)
                session.invalidate();
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }

    }
}
