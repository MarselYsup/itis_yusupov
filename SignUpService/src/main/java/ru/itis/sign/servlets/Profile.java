package ru.itis.sign.servlets;


import ru.itis.sign.models.Avatar;
import ru.itis.sign.models.User;
import ru.itis.sign.services.AvatarService;
import ru.itis.sign.services.CopyService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
@WebServlet("/profile")
@MultipartConfig
public class Profile extends HttpServlet {
    private AvatarService avatarService;
    private static String path;
    @Override
    public void init(ServletConfig config) throws ServletException {
        avatarService = (AvatarService) config.getServletContext().getAttribute("avatarService");
        path = (String) config.getServletContext().getAttribute("pathForAvatar");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("User") != null) {
            User user = (User) request.getSession().getAttribute("User");
            if(!avatarService.getAvatar(user.getId()).isEmpty()) {
                CopyService.copyDirectoryCompatibityMode(new File(path),new File("..\\webapps\\ROOT\\uploads"));
                Avatar avatar = avatarService.getAvatar(user.getId()).get();
                request.setAttribute("path",path.replace("\\","/")+avatar.getStorageFileName()+"."+avatar.getType());
            }
            request.setAttribute("user",user);
            request.getRequestDispatcher("jsp/profile.jsp").forward(request, response);
        } else {
            response.sendRedirect("/sign-in");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        avatarService.saveAvatarToStorage(req, path);
        resp.sendRedirect("/profile");
    }
}



