package com.sivalabs.usermanagement.domain.registration;

import com.sivalabs.usermanagement.entities.exceptions.EmptyRequiredFieldException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
class CreateUserRequestValidator {


  @NotNull
  public CreateUserRequest validate(@NotNull final CreateUserRequest request) {
    if (isBlank(request.getName())) {
      throw new EmptyRequiredFieldException("Name");
    }
    if (isBlank(request.getEmail())) {
      throw new EmptyRequiredFieldException("Email");
    }

    return request;
  }

  private boolean isBlank(@NotNull final String string) {
    return string == null || string.trim().length() == 0;
  }
}
