package com.sivalabs.usermanagement.domain.registration;

import com.sivalabs.usermanagement.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
class CreateUserRequestValidator {
    private final UserRepository userRepository;

    public List<String> validate(CreateUserRequest request) {
        List<String> errors = new ArrayList<>(0);
        if(isBlank(request.getName())) {
            errors.add("Name is required");
        }
        if(isBlank(request.getEmail())) {
            errors.add("Email is required");
        } else if(userRepository.existsByEmail(request.getEmail())) {
            errors.add("User already registered with email " + request.getEmail());
        }
        return errors;
    }

    private boolean isBlank(String string) {
        return string == null || string.trim().length() == 0;
    }
}
