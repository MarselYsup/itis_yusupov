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
import java.util.List;

@WebServlet("/myAnimeList")
public class UserAnimeListServlet extends HttpServlet {
    private UserAnimeService userAnimeService;
    private AnimeService animeService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.userAnimeService = (UserAnimeService) config.getServletContext().getAttribute("userAnimeService");
        this.animeService = (AnimeService) config.getServletContext().getAttribute("animeService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<AnimeUser> animeList = userAnimeService.getListAnime(user.getUserId());
        req.setAttribute("animeList",animeList);
        req.getRequestDispatcher("view/jsp/myAnimeList.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
