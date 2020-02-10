package com.hw.taskmanager.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        this("Not found");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
