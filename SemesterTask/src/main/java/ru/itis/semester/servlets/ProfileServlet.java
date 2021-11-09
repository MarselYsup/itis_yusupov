package ru.itis.semester.servlets;

import ru.itis.semester.models.Avatar;
import ru.itis.semester.models.User;
import ru.itis.semester.services.AvatarService;
import ru.itis.semester.services.ProfileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@MultipartConfig
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private AvatarService avatarService;
    private ProfileService profileService;
    private static final String AVATAR_PATH ="/avatars?avatarId=";
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.avatarService = (AvatarService) config.getServletContext().getAttribute("avatarService");
        this.profileService = (ProfileService) config.getServletContext().getAttribute("profileService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if(user.getAvatarId()!=0) {
            req.setAttribute("path",AVATAR_PATH+user.getAvatarId());
        }
        req.getRequestDispatcher("view/jsp/profile.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Avatar avatar =  avatarService.upload(req.getPart("avatar"));
        User user = (User) req.getSession().getAttribute("user");
        user.setAvatarId(avatar.getAvatarId());
       profileService.setAvatarOnUser(user);
       resp.sendRedirect(req.getContextPath() +"/profile");
    }
}
