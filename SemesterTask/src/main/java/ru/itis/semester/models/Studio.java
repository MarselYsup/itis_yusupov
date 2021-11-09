package ru.itis.semester.models;

public class Studio {
    private Long studioId;
    private String name;
    private Long avatarId;

    public Studio(Long studioId, String name, Long avatarId) {
        this.studioId = studioId;
        this.name = name;
        this.avatarId = avatarId;
    }

    public Studio(String name, Long avatarId) {
        this.name = name;
        this.avatarId = avatarId;
    }

    public Studio(String name) {
        this.name = name;
        this.avatarId = 0L;
    }

    public Long getStudioId() {
        return studioId;
    }

    public void setStudioId(Long studioId) {
        this.studioId = studioId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }
}
