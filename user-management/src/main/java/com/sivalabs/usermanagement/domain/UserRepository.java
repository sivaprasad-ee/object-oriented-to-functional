package com.sivalabs.usermanagement.domain;

import com.sivalabs.usermanagement.domain.registration.CreateUserRequest;

public interface UserRepository  {
    User save(CreateUserRequest request);
    void deleteAll();
}
