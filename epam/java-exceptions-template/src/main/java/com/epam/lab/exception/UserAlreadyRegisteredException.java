package com.epam.lab.exception;

public class UserAlreadyRegisteredException  extends Exception {

    public UserAlreadyRegisteredException(String login) {
        super("Пользователь с логином '" + login + "' уже зарегистрирован");
    }
}