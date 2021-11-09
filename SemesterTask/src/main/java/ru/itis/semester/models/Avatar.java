package ru.itis.semester.models;

public class Avatar {
    private Long avatarId;
    private String originalName;
    private String storageName;
    private Long size;
    private String mimeType;

    public Avatar(String originalName, String storageName, Long size, String mimeType) {
        this.originalName = originalName;
        this.storageName = storageName;
        this.size = size;
        this.mimeType = mimeType;
    }

    public Avatar(Long avatarId, String originalName, String storageName, Long size, String mimeType) {
        this.avatarId = avatarId;
        this.originalName = originalName;
        this.storageName = storageName;
        this.size = size;
        this.mimeType = mimeType;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "avatarId=" + avatarId +
                ", originalName='" + originalName + '\'' +
                ", storageName='" + storageName + '\'' +
                ", size=" + size +
                ", mimeType='" + mimeType + '\'' +
                '}';
    }
}
