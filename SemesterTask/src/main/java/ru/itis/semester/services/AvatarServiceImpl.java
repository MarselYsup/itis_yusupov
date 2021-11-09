package ru.itis.semester.services;

import ru.itis.semester.dao.AvatarRepository;
import ru.itis.semester.models.Avatar;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;
//TODO продумать ошибки с загрузкой аватара!
public class AvatarServiceImpl implements AvatarService{
    private final AvatarRepository avatarRepository;
    private static final String STORAGE_PATH ="C:\\storage\\";

    public AvatarServiceImpl(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }


    @Override
    public Avatar upload(Part part) {
        String originalName = part.getSubmittedFileName();
        String storageName = UUID.randomUUID().toString()+ "." + originalName.split("\\.")[1];
        Long size = part.getSize();
        String mimeType = part.getContentType();
        Avatar avatar = new Avatar(originalName,storageName,size,mimeType);
        avatar = avatarRepository.save(avatar);
        try {
            Files.copy(part.getInputStream(), Paths.get(STORAGE_PATH + avatar.getStorageName()));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return avatar;
    }

    @Override
    public void download(Long avatarId, HttpServletResponse response) {
        Avatar avatar = avatarRepository.findById(avatarId).get();
        response.setContentType(avatar.getMimeType());
        response.setHeader("Content-Length", String.valueOf(avatar.getSize()));
        response.setHeader("Content-Disposition", "fileName=\"" + avatar.getOriginalName() + "\"");
        try {
            Files.copy(Paths.get(STORAGE_PATH + avatar.getStorageName()), response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }
}
