package ru.itis.sign.models;

public class Avatar {
    private Long id;
    private String originalFileName;
    private String storageFileName;
    private Long size;
    private String type;
    private Long userId;

    public Avatar(Long id, String originalFileName, String storageFileName, Long size, String type, Long userId) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.storageFileName = storageFileName;
        this.size = size;
        this.type = type;
        this.userId = userId;
    }

    public Avatar(String originalFileName, String storageFileName, Long size, String type, Long userId) {
        this.originalFileName = originalFileName;
        this.storageFileName = storageFileName;
        this.size = size;
        this.type = type;
        this.userId = userId;
    }

    public Avatar(String originalFileName, String storageFileName, Long size, String type) {
        this.originalFileName = originalFileName;
        this.storageFileName = storageFileName;
        this.size = size;
        this.type = type;
    }

    public Avatar(Long id, String originalFileName, String storageFileName, Long size, String type) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.storageFileName = storageFileName;
        this.size = size;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getStorageFileName() {
        return storageFileName;
    }

    public void setStorageFileName(String storageFileName) {
        this.storageFileName = storageFileName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "id=" + id +
                ", originalFileName='" + originalFileName + '\'' +
                ", storageFileName='" + storageFileName + '\'' +
                ", size=" + size +
                ", type='" + type + '\'' +
                ", user_id=" + userId +
                '}';
    }
}
