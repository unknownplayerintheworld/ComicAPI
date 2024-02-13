package com.hung.comicapi.exception;

public class AccountAlreadyExistsException extends RuntimeException{
    public AccountAlreadyExistsException(String username) {
        super("User with username '" + username + "' already exists.");
    }
}
