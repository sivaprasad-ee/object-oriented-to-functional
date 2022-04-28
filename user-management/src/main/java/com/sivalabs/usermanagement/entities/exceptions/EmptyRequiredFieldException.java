package com.sivalabs.usermanagement.entities.exceptions;

public class EmptyRequiredFieldException extends RuntimeException {

  public EmptyRequiredFieldException(String field) {
    super("Field '" + field + "' is required");
  }

}
