package ru.itis.semester.services;

import ru.itis.semester.dao.UserAnimeRepository;
import ru.itis.semester.dao.UserRepository;
import ru.itis.semester.models.AnimeUser;

import java.util.List;
import java.util.Optional;

public class UserAnimeServiceImpl implements UserAnimeService{
   private final UserAnimeRepository animeRepository;

    public UserAnimeServiceImpl(UserAnimeRepository animeRepository ) {
        this.animeRepository = animeRepository;

    }

    @Override
    public void save(AnimeUser animeUser) {
        animeRepository.save(animeUser);
    }

    @Override
    public Optional<AnimeUser> getAnimeByIdUserAndAnime(Long userId, Long animeId) {
        return animeRepository.findByUserIdAndAnimeId(userId,animeId);
    }

    @Override
    public List<AnimeUser> getListAnime(Long userId) {
        return animeRepository.findByUserId(userId);
    }

    @Override
    public void updateRating(Integer rating, Long userId, Long animeId) {
        animeRepository.updateRating(rating,userId,animeId);
    }
}
