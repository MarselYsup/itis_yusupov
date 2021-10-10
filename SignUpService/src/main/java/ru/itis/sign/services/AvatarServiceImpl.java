package ru.itis.sign.services;

import ru.itis.sign.database.AvatarRepository;
import ru.itis.sign.models.Avatar;
import ru.itis.sign.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

public class AvatarServiceImpl implements AvatarService{
    private final AvatarRepository avatarRepository;

    public AvatarServiceImpl(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }


    @Override
    public Avatar saveAvatarToStorage(HttpServletRequest req,String path) {
        Part part;
        try {
            part = req.getPart("avatar");

        } catch (ServletException | IOException e) {
            throw new IllegalArgumentException(e);
        }
        User user = (User) req.getSession().getAttribute("User");
        Avatar avatar = new Avatar(part.getSubmittedFileName(),UUID.randomUUID().toString(),part.getSize(), part.getContentType().split("/")[1],user.getId());
        avatar = avatarRepository.save(avatar);
        try {
            Files.copy(part.getInputStream(),Paths.get(path+avatar.getStorageFileName()+"."+avatar.getType()));
        } catch (IOException e) {
           throw new IllegalArgumentException(e);
        }
        return avatar;
    }

    @Override
    public Optional<Avatar> getAvatar(Long userId) {
        return avatarRepository.findByUserId(userId);
    }


}
