package com.sivalabs.usermanagement.domain.registration;

import com.sivalabs.usermanagement.entities.exceptions.EmptyRequiredFieldException;
import org.springframework.stereotype.Component;

@Component
class CreateUserRequestValidator {


  public CreateUserRequest validate(CreateUserRequest request) {
    if (isBlank(request.getName())) {
      throw new EmptyRequiredFieldException("Name");
    }
    if (isBlank(request.getEmail())) {
      throw new EmptyRequiredFieldException("Email");
    }

    return request;
  }

  private boolean isBlank(String string) {
    return string == null || string.trim().length() == 0;
  }
}
