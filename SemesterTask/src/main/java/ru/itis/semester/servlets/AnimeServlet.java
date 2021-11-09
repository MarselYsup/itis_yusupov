package ru.itis.semester.servlets;

import ru.itis.semester.models.*;
import ru.itis.semester.services.AnimeService;
import ru.itis.semester.services.AvatarService;
import ru.itis.semester.services.StudioService;
import ru.itis.semester.services.UserAnimeService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
//TODO вытаскивать путь через listener
@WebServlet("/anime")
public class AnimeServlet extends HttpServlet {
    private AnimeService animeService;
    private UserAnimeService userAnimeService;
    private StudioService studioService;
    private static final String AVATAR_PATH ="/avatars?avatarId=";
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.animeService = (AnimeService) config.getServletContext().getAttribute("animeService");
        this.studioService = (StudioService) config.getServletContext().getAttribute("studioService");
        this.userAnimeService = (UserAnimeService) config.getServletContext().getAttribute("userAnimeService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long animeId = Long.valueOf(req.getParameter("animeId"));
        User user = (User) req.getSession().getAttribute("user");
        Optional<Anime> animeOptional = animeService.getAnime(animeId);
        if(animeOptional.isPresent()) {
            animeService.updateRating(animeId);
            Anime anime = animeOptional.get();
            Studio studio = studioService.getStudio(anime.getStudioId()).get();
            req.setAttribute("anime",anime);
            req.setAttribute("avatarAnime",AVATAR_PATH+anime.getAvatarId());
            req.setAttribute("studio",studio);
            req.setAttribute("avatarStudio",AVATAR_PATH+studio.getAvatarId());

            Optional<AnimeUser> animeUserOptional = userAnimeService.getAnimeByIdUserAndAnime(user.getUserId(),anime.getAnimeId());
            if(animeUserOptional.isPresent()) {
                req.setAttribute("status", animeUserOptional.get().getStatus());
            }

            req.getRequestDispatcher("view/jsp/anime.jsp").forward(req, resp);
        }
        else resp.sendRedirect(req.getContextPath() +"/animeList");
    }
}
