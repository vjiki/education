package com.epam.lab.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("Пользователь с таким логином не найден");
    }
}