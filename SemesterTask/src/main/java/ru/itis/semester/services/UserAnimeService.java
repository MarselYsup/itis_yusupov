package ru.itis.semester.services;

import ru.itis.semester.models.AnimeUser;

import java.util.List;
import java.util.Optional;

public interface UserAnimeService {
    void save(AnimeUser animeUser);
    Optional<AnimeUser> getAnimeByIdUserAndAnime(Long userId,Long animeId);
    List<AnimeUser> getListAnime(Long userId);
    void updateRating(Integer rating,Long userId,Long animeId);
}
