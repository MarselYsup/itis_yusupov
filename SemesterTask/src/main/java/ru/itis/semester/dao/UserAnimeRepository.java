package ru.itis.semester.dao;

import ru.itis.semester.dao.base.CrudRepository;
import ru.itis.semester.models.AnimeUser;

import java.util.List;
import java.util.Optional;

public interface UserAnimeRepository extends CrudRepository<AnimeUser,Long> {
    List<AnimeUser> findByUserId(Long userId);
    Optional<AnimeUser> findByUserIdAndAnimeId(Long userId, Long animeId);
    void updateRating(Integer rating,Long userId,Long animeId);
    List<AnimeUser> findByAnimeId(Long animeId);
}
