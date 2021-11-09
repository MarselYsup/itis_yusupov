package ru.itis.semester.servlets;

import ru.itis.semester.models.AnimeUser;
import ru.itis.semester.models.User;
import ru.itis.semester.services.AnimeService;
import ru.itis.semester.services.UserAnimeService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addList")
public class AddAnimeServlet extends HttpServlet {
    private UserAnimeService userAnimeService;
    private AnimeService animeService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.userAnimeService = (UserAnimeService) config.getServletContext().getAttribute("userAnimeService");
        this.animeService = (AnimeService) config.getServletContext().getAttribute("animeService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() +"/myAnimeList");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Long animeId = Long.valueOf(req.getParameter("animeId"));
        if(req.getParameter("rating")!=null) {
            Integer rating = Integer.valueOf(req.getParameter("rating"));
            userAnimeService.updateRating(rating,user.getUserId(),animeId);
            animeService.updateRating(animeId);
        }
        else {
            Integer status = Integer.valueOf(req.getParameter("status"));
            AnimeUser animeUser = new AnimeUser(animeId, user.getUserId(), status);
            userAnimeService.save(animeUser);
        }

        resp.sendRedirect(req.getContextPath() + "/myAnimeList");

    }
}
