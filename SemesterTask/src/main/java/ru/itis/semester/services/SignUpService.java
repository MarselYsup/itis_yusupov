package ru.itis.semester.services;

import ru.itis.semester.dto.SignUpForm;

public interface SignUpService {
    boolean validate(SignUpForm signUpForm);
    void save(SignUpForm signUpForm);
}
