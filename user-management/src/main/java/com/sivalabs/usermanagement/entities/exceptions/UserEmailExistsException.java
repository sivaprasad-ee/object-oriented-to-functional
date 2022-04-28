package com.sivalabs.usermanagement.entities.exceptions;

public class UserEmailExistsException extends RuntimeException {

  public UserEmailExistsException(String email) {
    super("User already registered with email '" + email + "'");
  }

}
