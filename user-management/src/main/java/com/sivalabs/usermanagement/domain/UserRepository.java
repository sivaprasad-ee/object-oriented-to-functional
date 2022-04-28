package com.sivalabs.usermanagement.domain;


import com.sivalabs.usermanagement.domain.registration.CreateUserRequest;
import com.sivalabs.usermanagement.entities.User;

public interface UserRepository {

  User save(CreateUserRequest user);

  void deleteAllInBatch();

}
