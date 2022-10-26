package com.emresahna.loginwithreact.Service;

import com.emresahna.loginwithreact.Dto.LoginResponse;
import com.emresahna.loginwithreact.Dto.LoginRequest;
import com.emresahna.loginwithreact.Dto.RegisterDto;
import com.emresahna.loginwithreact.Entity.User;
import com.emresahna.loginwithreact.Exception.UserAlreadyCreatedException;
import com.emresahna.loginwithreact.Repository.UserRepository;
import com.emresahna.loginwithreact.Security.TokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse login(LoginRequest form) {
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword()));
            User user = userRepository.findUserByUsername(((UserDetails) auth.getPrincipal()).getUsername()).orElse(null);
            LoginResponse response = new LoginResponse();
            response.setUsername(form.getUsername());
            response.setToken(TokenUtils.createToken(user.getId(),user.getUsername()));
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public User register(RegisterDto user) throws UserAlreadyCreatedException {
        User commed = userRepository.findUserByUsername(user.getUsername()).orElse(null);
        if(commed != null){
            throw new UserAlreadyCreatedException("User already created");
        }
        User registerUser = new User();
        registerUser.setUsername(user.getUsername());
        registerUser.setPassword(passwordEncoder.encode(user.getPassword()));
        registerUser.setEmail(user.getEmail());
        return userRepository.save(registerUser);
    }

    public LoginResponse isLogged(String token){
        token = token.replace("Bearer ","");
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        LoginResponse response = new LoginResponse();
        response.setUsername(name);
        response.setToken(token);
        return response;
    }
}
