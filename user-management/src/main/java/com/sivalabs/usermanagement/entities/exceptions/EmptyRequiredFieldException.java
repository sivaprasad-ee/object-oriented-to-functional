package com.sivalabs.usermanagement.entities.exceptions;

import org.jetbrains.annotations.NotNull;

public class EmptyRequiredFieldException extends RuntimeException {

  public EmptyRequiredFieldException(@NotNull final String field) {
    super("Field '" + field + "' is required");
  }

}
