package ru.itis.semester.servlets;

import ru.itis.semester.models.Studio;
import ru.itis.semester.services.StudioService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
@MultipartConfig
@WebServlet("/add-studio")
public class StudioCreateServlet extends HttpServlet {
    private StudioService studioService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.studioService = (StudioService) config.getServletContext().getAttribute("studioService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/jsp/addStudio.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("avatar");
        String name  = req.getParameter("name");
        Studio studio = new Studio(name);
        studioService.save(studio,part);
        resp.sendRedirect(req.getContextPath() +"/add-studio");

    }
}
