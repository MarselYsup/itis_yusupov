package ru.itis.semester.services;

import ru.itis.semester.models.Studio;

import javax.servlet.http.Part;
import java.util.Optional;

public interface StudioService {
    void save(Studio studio, Part part);
    Optional<Studio> getStudio(Long studioId);
}
