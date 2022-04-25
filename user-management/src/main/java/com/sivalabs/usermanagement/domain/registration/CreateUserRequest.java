package com.sivalabs.usermanagement.domain.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserRequest {
    private String name;
    private String email;
    private String phone;
}
