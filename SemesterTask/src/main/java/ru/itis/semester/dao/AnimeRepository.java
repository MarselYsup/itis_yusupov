package ru.itis.semester.dao;

import ru.itis.semester.dao.base.CrudRepository;
import ru.itis.semester.models.Anime;

public interface AnimeRepository extends CrudRepository<Anime,Long> {
    void updateRating(Integer rating,Long animeId);
}
