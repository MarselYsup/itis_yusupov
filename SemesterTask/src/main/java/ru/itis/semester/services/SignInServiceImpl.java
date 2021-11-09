package ru.itis.semester.services;

import ru.itis.semester.dao.UserRepository;
import ru.itis.semester.dto.SignInForm;
import ru.itis.semester.models.User;

import java.util.Optional;

public class SignInServiceImpl implements SignInService{
    private final UserRepository userRepository;

    public SignInServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean checkAccount(SignInForm form) {
        Optional<User> user = userRepository.findByUsername(form.getUsername());
        if(user.isEmpty()) return false;
        if(!user.get().getPassword().equals(form.getPassword())) return false;

        return true;
    }

    @Override
    public User getUserByUsername(String name) {
        return userRepository.findByUsername(name).get();
    }
    @Override
    public boolean checkAdmin(Long userId) {
        Optional<Long> id = userRepository.findAdminById(userId);
        return id.isPresent();
    }
}
