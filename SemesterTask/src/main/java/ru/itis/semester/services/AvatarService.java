package ru.itis.semester.services;

import ru.itis.semester.models.Avatar;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public interface AvatarService {
    Avatar upload(Part part);
    void download(Long avatarId, HttpServletResponse response);

}
