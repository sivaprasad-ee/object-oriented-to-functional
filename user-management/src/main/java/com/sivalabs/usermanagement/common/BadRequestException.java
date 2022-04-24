package com.sivalabs.usermanagement.common;

import java.util.List;

public class BadRequestException extends RuntimeException {
    private String message;
    private List<String> errors;

    public BadRequestException(String message) {
        super(message);
        this.message = message;
    }

    public BadRequestException(String message, List<String> errors) {
        super(message);
        this.message = message;
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
