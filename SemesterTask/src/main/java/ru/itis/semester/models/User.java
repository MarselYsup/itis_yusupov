package ru.itis.semester.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long userId;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private Long avatarId;
    private List<AnimeUser> animeList;

    public User() {
    }

    public User(Long userId, String username, String firstName, String lastName, String password, Long avatarId) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.avatarId = avatarId;
        animeList = new ArrayList<>();
    }

    public User(String username, String firstName, String lastName, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        animeList = new ArrayList<>();
    }

    public User(Long userId, String username, String firstName,
                String lastName, String password) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        animeList = new ArrayList<>();
    }

    public User(Long userId, String username, String firstName, String lastName, String password,
                Long avatarId, List<AnimeUser> animeList) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.avatarId = avatarId;
        this.animeList = animeList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }

    public List<AnimeUser> getAnimeList() {
        return animeList;
    }

    public void setAnimeList(List<AnimeUser> animeList) {
        this.animeList = animeList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", avatarId=" + avatarId +
                ", animeList=" + animeList +
                '}';
    }
}
