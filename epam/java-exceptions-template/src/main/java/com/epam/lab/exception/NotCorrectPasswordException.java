package com.epam.lab.exception;

public class NotCorrectPasswordException extends Exception {

    public NotCorrectPasswordException() {
        super("Пароль введен неверно!");
    }
}