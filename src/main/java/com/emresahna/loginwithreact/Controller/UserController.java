package com.emresahna.loginwithreact.Controller;

import com.emresahna.loginwithreact.Dto.LoginRequest;
import com.emresahna.loginwithreact.Dto.LoginResponse;
import com.emresahna.loginwithreact.Dto.RegisterDto;
import com.emresahna.loginwithreact.Entity.User;
import com.emresahna.loginwithreact.Exception.UserAlreadyCreatedException;
import com.emresahna.loginwithreact.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterDto user) throws UserAlreadyCreatedException {
        User returnedUser = userService.register(user);
        return new ResponseEntity<>(returnedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest form){
        LoginResponse response = userService.login(form);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/is_logged_in")
    public ResponseEntity<LoginResponse> stillLogin(@RequestHeader("Authorization") String token){
        LoginResponse response = userService.isLogged(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
