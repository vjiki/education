package com.epam.lab.exception;

public class NotAccessException extends Exception {

    public NotAccessException() {
        super("Недостаточно прав для выполнения операции");
    }
}