package com.emresahna.loginwithreact.Service;

import com.emresahna.loginwithreact.Dto.LoginDto;
import com.emresahna.loginwithreact.Dto.RegisterDto;
import com.emresahna.loginwithreact.Entity.User;
import com.emresahna.loginwithreact.Exception.UserAlreadyCreatedException;
import com.emresahna.loginwithreact.Exception.UserIncorrectLoginAttemptException;
import com.emresahna.loginwithreact.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(LoginDto user) {
        return userRepository.findUserByUsernameAndPassword(user.getUsername(),user.getPassword()).orElseThrow(() -> new UserIncorrectLoginAttemptException("Incorrect login attempt."));
    }

    public User register(RegisterDto user) throws UserAlreadyCreatedException {
        User commed = userRepository.findUserByUsername(user.getUsername()).orElse(null);
        if(commed != null){
            throw new UserAlreadyCreatedException("User already created");
        }
        User registerUser = new User();
        registerUser.setUsername(user.getUsername());
        registerUser.setPassword(user.getPassword());
        registerUser.setEmail(user.getEmail());
        return userRepository.save(registerUser);
    }
}
