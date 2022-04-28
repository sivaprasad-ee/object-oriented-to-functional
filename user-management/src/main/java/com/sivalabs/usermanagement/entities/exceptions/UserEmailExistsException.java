package com.sivalabs.usermanagement.entities.exceptions;

import org.jetbrains.annotations.NotNull;

public class UserEmailExistsException extends RuntimeException {

  public UserEmailExistsException(@NotNull final String email) {
    super("User already registered with email '" + email + "'");
  }

}
