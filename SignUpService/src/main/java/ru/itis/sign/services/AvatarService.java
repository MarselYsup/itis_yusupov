package ru.itis.sign.services;

import ru.itis.sign.models.Avatar;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Optional;

public interface AvatarService {
    Avatar saveAvatarToStorage(HttpServletRequest req,String path);
    Optional<Avatar> getAvatar(Long userId);
}
