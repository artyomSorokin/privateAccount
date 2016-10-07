package net.sorokin.controller;


import net.sorokin.dao.PhotoDao;
import net.sorokin.dao.PhotoDaoJdbcImpl;
import net.sorokin.entity.Photo;
import net.sorokin.entity.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;



@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
        maxFileSize=1024*1024*10,      // 10MB
        maxRequestSize=1024*1024*50)   // 50MB
public class UploadController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        PhotoDao photoDao = new PhotoDaoJdbcImpl();

        for (Part part : req.getParts()) {
            String fileName = extractFileName(part);
            byte [] array = null;
            InputStream in = part.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1];
            int n = 0;
            while ((n=in.read(buffer))>=0) {
                baos.write(buffer, 0, n);
            }
            in.close();
            array = baos.toByteArray();
            Photo photo = new Photo();
            photo.setEmail(user.getEmail());
            photo.setName(fileName);
            photo.setImage(array);
            try {
                photoDao.insertPhoto(photo);
            } catch (SQLException e) {
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }
        }
        req.getRequestDispatcher("/account").forward(req, resp);
        return;
    }



    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}
