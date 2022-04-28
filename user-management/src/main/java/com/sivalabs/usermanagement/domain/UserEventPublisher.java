package com.sivalabs.usermanagement.domain;


import com.sivalabs.usermanagement.entities.User;

public interface UserEventPublisher {

  void userCreated(User savedUser);

}
