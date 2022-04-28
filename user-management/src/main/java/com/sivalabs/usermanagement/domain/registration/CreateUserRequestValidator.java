package com.sivalabs.usermanagement.domain.registration;

import com.sivalabs.usermanagement.entities.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class CreateUserRequestValidator {


  public CreateUserRequest validate(CreateUserRequest request) {
    List<String> errors = new ArrayList<>(0);
    if (isBlank(request.getName())) {
      errors.add("Name is required");
    }
    if (isBlank(request.getEmail())) {
      errors.add("Email is required");
    }
    if (!errors.isEmpty()) {
      throw new BadRequestException("Invalid user registration request", errors);
    }

    return request;
  }

  private boolean isBlank(String string) {
    return string == null || string.trim().length() == 0;
  }
}
