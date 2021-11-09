package ru.itis.semester.servlets;

import ru.itis.semester.models.Anime;
import ru.itis.semester.models.Genre;
import ru.itis.semester.models.Studio;
import ru.itis.semester.services.AnimeService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MultipartConfig
@WebServlet("/add-anime")
public class AnimeCreateServlet extends HttpServlet {
    private AnimeService animeService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        animeService = (AnimeService) config.getServletContext().getAttribute("animeService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Genre> genres = animeService.getGenres();
        List<Studio> studios = animeService.getStudios();
        req.setAttribute("studios",studios);
        req.setAttribute("genres",genres);
        req.getRequestDispatcher("view/jsp/addAnime.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title  = req.getParameter("title");
        Integer year = Integer.valueOf(req.getParameter("year"));
        Integer episodes = Integer.valueOf(req.getParameter("episodes"));
        String description = req.getParameter("description");
        Map<Long,String> hashMap = new HashMap<>();
        String[] genres = req.getParameterValues("genre");
        for (int i = 0; i < genres.length; i++) {
            String [] genre = genres[i].split(":");
            hashMap.put(Long.parseLong(genre[1]),genre[0]);
        }
        Part part = req.getPart("avatar");
        Long studioId = Long.valueOf(req.getParameter("studio"));
        Anime anime = new Anime(title,year,episodes,description,hashMap,studioId);
        animeService.save(anime,part);
        resp.sendRedirect(req.getContextPath() +"/add-anime");
    }
}
