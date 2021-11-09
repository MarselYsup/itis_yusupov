package ru.itis.semester.services;

import ru.itis.semester.dao.StudioRepository;
import ru.itis.semester.models.Avatar;
import ru.itis.semester.models.Studio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.util.Optional;

public class StudioServiceImpl implements StudioService{
    private final AvatarService avatarService;
    private final StudioRepository studioRepository;

    public StudioServiceImpl(AvatarService avatarService, StudioRepository studioRepository) {
        this.avatarService = avatarService;
        this.studioRepository = studioRepository;
    }

    @Override
    public void save(Studio studio, Part part) {
        Avatar avatar = avatarService.upload(part);
        studio.setAvatarId(avatar.getAvatarId());
        studioRepository.save(studio);
    }

    @Override
    public Optional<Studio> getStudio(Long studioId) {
        return studioRepository.findById(studioId);
    }
}
