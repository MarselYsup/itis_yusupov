package ru.itis.semester.models;

public class AnimeUser {
    private Long userId;
    private Long animeId;
    private String title;
    private Integer userRating;
    private Integer status;

    public AnimeUser(Long animeId, Long userId, Integer status) {
        this.animeId = animeId;
        this.userId = userId;
        this.status = status;
        this.userRating = 0;
    }

    public AnimeUser(Long animeId, Long userId, Integer userRating, Integer status) {
        this.animeId = animeId;
        this.userId = userId;
        this.userRating = userRating;
        this.status = status;
    }

    public Long getAnimeId() {
        return animeId;
    }

    public void setAnimeId(Long animeId) {
        this.animeId = animeId;
    }


    public Integer getUserRating() {
        return userRating;
    }

    public void setUserRating(Integer userRating) {
        this.userRating = userRating;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
