package com.sivalabs.usermanagement.domain.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreatedEvent {
    private Long id;
    private String name;
    private String email;
    private String phone;
}
