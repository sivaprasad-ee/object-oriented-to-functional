package com.sivalabs.usermanagement.domain;

import java.util.Collections;
import java.util.List;

public class BadRequestException extends RuntimeException {
    private final List<String> errors;

    public BadRequestException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }
}
