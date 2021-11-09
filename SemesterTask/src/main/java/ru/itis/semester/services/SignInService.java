package ru.itis.semester.services;

import ru.itis.semester.dto.SignInForm;
import ru.itis.semester.models.User;

public interface SignInService {
    boolean checkAccount(SignInForm form);
    User getUserByUsername(String name);
    boolean checkAdmin(Long userId);
}
