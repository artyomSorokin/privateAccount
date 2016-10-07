package net.sorokin.controller;


import net.sorokin.dao.PhotoDao;
import net.sorokin.dao.PhotoDaoJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;


public class ImageController extends HttpServlet {

    private PhotoDao photoDao = new PhotoDaoJdbcImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idStr = req.getParameter("id");
            OutputStream oImage;
            byte[] sImageBytes;

            if (idStr != null && !idStr.equals("")) {
                final Integer id = Integer.valueOf(idStr);
                sImageBytes =  photoDao.selectImageById(id);

                resp.setContentType("image/gif");
                oImage=resp.getOutputStream();
                oImage.write(sImageBytes);
                oImage.flush();
                oImage.close();
            }
        }
        catch(Exception ex){
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
}
