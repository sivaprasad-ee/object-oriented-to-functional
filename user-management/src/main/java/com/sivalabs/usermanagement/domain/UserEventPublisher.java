package com.sivalabs.usermanagement.domain;


import com.sivalabs.usermanagement.entities.User;

public interface UserEventPublisher {

  User userCreated(User savedUser);

}
