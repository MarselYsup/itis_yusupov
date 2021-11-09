package ru.itis.semester.servlets;

import ru.itis.semester.services.AvatarService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/avatars")
public class AvatarDownloadServlet extends HttpServlet {
    private AvatarService avatarService;
    @Override
    public void init(ServletConfig config) {
        this.avatarService = (AvatarService) config.getServletContext().getAttribute("avatarService");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long avatarId = Long.parseLong(request.getParameter("avatarId"));
        avatarService.download(avatarId,response);
    }
}
