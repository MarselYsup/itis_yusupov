package ru.itis.semester.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anime {
    private Long animeId;
    private String animeTitle;
    private Integer year;
    private Integer countOfEpisodes;
    private String description;
    private Integer rating;
    private Map<Long,String> genres;
    private Long studioId;
    private Long avatarId;

    public Anime() {
    }


    public Anime(String animeTitle, Integer year, Integer countOfEpisodes,
                 String description, Map<Long, String> genres, Long studioId) {
        this.animeTitle = animeTitle;
        this.year = year;
        this.countOfEpisodes = countOfEpisodes;
        this.description = description;
        this.genres = genres;
        this.studioId = studioId;
        this.rating = 0;
    }

    public Anime(String animeTitle, Integer year, Integer countOfEpisodes, String description, Map<Long,String> genres,
                 Long studioId, Long avatarId) {
        this.animeTitle = animeTitle;
        this.year = year;
        this.countOfEpisodes = countOfEpisodes;
        this.description = description;
        this.genres = genres;
        this.studioId = studioId;
        this.avatarId = avatarId;
        rating = 0;
    }

    public Anime(String animeTitle, Integer year, Integer countOfEpisodes, String description,
                 Integer rating, Map<Long,String> genres) {
        this.animeTitle = animeTitle;
        this.year = year;
        this.countOfEpisodes = countOfEpisodes;
        this.description = description;
        this.rating = rating;
        this.genres = genres;
    }

    public Anime(String animeTitle, Integer year, Integer countOfEpisodes, String description, Integer rating,
                 Map<Long,String> genres, Long studioId, Long avatarId) {
        this.animeTitle = animeTitle;
        this.year = year;
        this.countOfEpisodes = countOfEpisodes;
        this.description = description;
        this.rating = rating;
        this.genres = genres;
        this.studioId = studioId;
        this.avatarId = avatarId;
    }

    public Anime(Long animeId, String animeTitle, Integer year, Integer countOfEpisodes, String description, Integer rating,
                 Map<Long,String> genres, Long studioId, Long avatarId) {
        this.animeId = animeId;
        this.animeTitle = animeTitle;
        this.year = year;
        this.countOfEpisodes = countOfEpisodes;
        this.description = description;
        this.rating = rating;
        this.genres = genres;
        this.studioId = studioId;
        this.avatarId = avatarId;
    }



    public Long getAnimeId() {
        return animeId;
    }

    public void setAnimeId(Long animeId) {
        this.animeId = animeId;
    }

    public String getAnimeTitle() {
        return animeTitle;
    }

    public void setAnimeTitle(String animeTitle) {
        this.animeTitle = animeTitle;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getCountOfEpisodes() {
        return countOfEpisodes;
    }

    public void setCountOfEpisodes(Integer countOfEpisodes) {
        this.countOfEpisodes = countOfEpisodes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Map<Long, String> getGenres() {
        return genres;
    }

    public void setGenres(Map<Long, String> genres) {
        this.genres = genres;
    }

    public Long getStudioId() {
        return studioId;
    }

    public void setStudioId(Long studioId) {
        this.studioId = studioId;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }

    @Override
    public String toString() {
        return "Anime{" +
                "animeId=" + animeId +
                ", animeTitle='" + animeTitle + '\'' +
                ", year=" + year +
                ", countOfEpisodes=" + countOfEpisodes +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", genres=" + genres +
                ", studioId=" + studioId +
                ", avatarId=" + avatarId +
                '}';
    }
}
