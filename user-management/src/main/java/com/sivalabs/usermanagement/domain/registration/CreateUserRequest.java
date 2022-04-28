package com.sivalabs.usermanagement.domain.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserRequest {

  private final String name;
  private final String email;
  private final String phone;

}
