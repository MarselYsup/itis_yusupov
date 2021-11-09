package ru.itis.semester.services;

import ru.itis.semester.dao.AnimeRepository;
import ru.itis.semester.dao.GenreRepository;
import ru.itis.semester.dao.StudioRepository;
import ru.itis.semester.dao.UserAnimeRepository;
import ru.itis.semester.models.*;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Optional;

public class AnimeServiceImpl implements AnimeService{
    private final UserAnimeRepository userAnimeRepository;
    private final GenreRepository genreRepository;
    private final StudioRepository studioRepository;
    private final AnimeRepository animeRepository;
    private final AvatarService avatarService;

    public AnimeServiceImpl(GenreRepository genreRepository, StudioRepository studioRepository,
                            AnimeRepository animeRepository, AvatarService avatarService,
                            UserAnimeRepository userAnimeRepository) {
        this.genreRepository = genreRepository;
        this.studioRepository = studioRepository;
        this.animeRepository = animeRepository;
        this.avatarService = avatarService;
        this.userAnimeRepository = userAnimeRepository;
    }

    @Override
    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    @Override
    public List<Studio> getStudios() {
        return studioRepository.findAll();
    }

    @Override
    public void save(Anime anime, Part part) {
        Avatar avatar = avatarService.upload(part);
        anime.setAvatarId(avatar.getAvatarId());
        animeRepository.save(anime);
    }

    @Override
    public List<Anime> getAllAnime() {
        return animeRepository.findAll();
    }

    @Override
    public Optional<Anime> getAnime(Long animeId) {
        return animeRepository.findById(animeId);
    }

    @Override
    public void updateRating(Long animeId) {
        List<AnimeUser> animes = userAnimeRepository.findByAnimeId(animeId);
        int count = 0;
        int sum = 0;
        for (AnimeUser anime:
             animes) {
            if(anime.getStatus()==2) {
                sum += anime.getUserRating();
                count++;
            }
        }
        int rating;
        if(count>0) {
            rating = sum / count;
        }else {
            rating = 0;
        }
        animeRepository.updateRating(rating,animeId);
    }
}
