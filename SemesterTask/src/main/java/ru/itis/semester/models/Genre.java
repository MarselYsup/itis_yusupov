package ru.itis.semester.models;

public class Genre {
    private Long genreId;
    private String title;

    public Genre(String title) {
        this.title = title;
    }

    public Genre(Long genreId, String title) {
        this.genreId = genreId;
        this.title = title;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
