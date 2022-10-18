package com.emresahna.loginwithreact.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserIncorrectLoginAttemptException extends RuntimeException{
    public UserIncorrectLoginAttemptException(String message) {
        super(message);
    }
}
