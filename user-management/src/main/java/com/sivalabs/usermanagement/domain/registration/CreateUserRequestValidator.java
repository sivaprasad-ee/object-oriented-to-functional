package com.sivalabs.usermanagement.domain.registration;

import com.sivalabs.usermanagement.domain.UserCreationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
class CreateUserRequestValidator {
    public void validate(CreateUserRequest request) {
        List<String> errors = new ArrayList<>(0);
        if(isBlank(request.getName())) {
            errors.add("Name is required");
        }
        if(isBlank(request.getEmail())) {
            errors.add("Email is required");
        }
        if(!errors.isEmpty()) {
            throw new UserCreationException("Invalid user registration request", errors);
        }
    }

    private boolean isBlank(String string) {
        return string == null || string.trim().length() == 0;
    }
}
