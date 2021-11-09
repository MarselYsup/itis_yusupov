package ru.itis.semester.services;

import ru.itis.semester.dao.UserRepository;
import ru.itis.semester.dto.SignUpForm;
import ru.itis.semester.models.User;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//TODO сделать ошибки с паролем,username и тд
public class SignUpServiceImpl implements SignUpService{
    private final UserRepository userRepository;

    public SignUpServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean validate(SignUpForm signUpForm) {
        Optional<User> user = userRepository.findByUsername(signUpForm.getUsername());
        if(user.isPresent()) return false;
        if(!isValidUsername(signUpForm.getUsername())||!isValidPassword(signUpForm.getPassword())) return false;
        return true;
    }

    @Override
    public void save(SignUpForm signUpForm) {
        User user = new User(signUpForm.getUsername(), signUpForm.getFirstName(),
                signUpForm.getLastName(), signUpForm.getPassword());
        userRepository.save(user);
    }

    private static boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,30}$";
        Pattern p = Pattern.compile(regex);
        if(password==null) return false;
        Matcher m = p.matcher(password);
        return m.matches();
    }
    private static boolean isValidUsername(String name)
    {

        // Regex to check valid username.
        String regex = "^[A-Za-z]\\w{5,30}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the username is empty
        // return false
        if (name == null) return false;

        // Pattern class contains matcher() method
        // to find matching between given username
        // and regular expression.
        Matcher m = p.matcher(name);

        // Return if the username
        // matched the ReGex
        return m.matches();
    }
}
