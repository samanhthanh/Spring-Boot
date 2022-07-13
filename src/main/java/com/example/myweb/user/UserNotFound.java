package com.example.myweb.user;

public class UserNotFound extends Throwable {
    public UserNotFound(String message) {
        super(message);
    }
}
