package ru.itis.semester.services;

import ru.itis.semester.dao.UserRepository;
import ru.itis.semester.models.User;

import java.util.Optional;

public class ProfileServiceImpl implements ProfileService{
    private final UserRepository userRepository;

    public ProfileServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void setAvatarOnUser(User user) {
        userRepository.updateAvatarForUser(user.getUserId(),user.getAvatarId());
    }


}
