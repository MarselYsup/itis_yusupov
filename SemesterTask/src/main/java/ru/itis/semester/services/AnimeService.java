package ru.itis.semester.services;

import ru.itis.semester.models.Anime;
import ru.itis.semester.models.Genre;
import ru.itis.semester.models.Studio;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Optional;

public interface AnimeService {
    List<Genre> getGenres();
    List<Studio> getStudios();
    void save(Anime anime, Part part);
    List<Anime> getAllAnime();
    Optional<Anime> getAnime(Long animeId);
    void updateRating(Long animeId);
}
