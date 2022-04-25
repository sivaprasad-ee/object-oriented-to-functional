package com.sivalabs.usermanagement.domain.registration;

import com.sivalabs.usermanagement.common.BadRequestException;
import com.sivalabs.usermanagement.common.ResourceAlreadyExistsException;
import com.sivalabs.usermanagement.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
class CreateUserRequestValidator {
    private final UserRepository userRepository;

    public void validate(CreateUserRequest request) {
        List<String> errors = new ArrayList<>(0);
        if(isBlank(request.getName())) {
            errors.add("Name is required");
        }
        if(isBlank(request.getEmail())) {
            errors.add("Email is required");
        }
        if(!errors.isEmpty()) {
            throw new BadRequestException("Invalid user registration request", errors);
        }
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("User already registered with email " + request.getEmail());
        }
    }

    private boolean isBlank(String string) {
        return string == null || string.trim().length() == 0;
    }
}
