package com.sivalabs.usermanagement.domain;


import com.sivalabs.usermanagement.domain.registration.CreateUserRequest;
import com.sivalabs.usermanagement.entities.User;

public interface UserRepository {

  boolean existsByEmail(String email);

  User save(CreateUserRequest user);

  void deleteAllInBatch();
}
