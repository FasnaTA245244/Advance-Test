package com.ust.websecurity.exception;

public class UserNotSubscribedException extends RuntimeException{
    public UserNotSubscribedException(String message) {
        super(message);
    }
}
