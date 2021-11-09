package ru.itis.semester.servlets;

import ru.itis.semester.models.Anime;
import ru.itis.semester.services.AnimeService;
import ru.itis.semester.services.AvatarService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.Map;

@WebServlet("/animeList")
public class AnimeListServlet extends HttpServlet {
    private AnimeService animeService;

    private static final String AVATAR_PATH ="/avatars?avatarId=";

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.animeService = (AnimeService) config.getServletContext().getAttribute("animeService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Anime> animeList = animeService.getAllAnime();
        Map<Long,String> avatars = new HashMap<>();
        for (Anime anime :
                animeList) {
            avatars.put(anime.getAnimeId(),AVATAR_PATH+anime.getAvatarId());
        }
        req.setAttribute("animeList",animeList);
        req.setAttribute("avatars",avatars);
        req.getRequestDispatcher("view/jsp/animeList.jsp").forward(req,resp);
    }
}
