package com.epam.lab.exception;

public class SimplePasswordException extends Exception {

    public SimplePasswordException() {
        super("Пароль не соответствует требованиям безопасности");
    }
}
