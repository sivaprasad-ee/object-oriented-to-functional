package com.sivalabs.usermanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;
    private String phone;
}
