package com.sivalabs.usermanagement.domain.registration;

import com.sivalabs.usermanagement.entities.User;
import com.sivalabs.usermanagement.domain.UserEventPublisher;
import com.sivalabs.usermanagement.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRegistrationService {

  private final UserRepository userRepository;
  private final CreateUserRequestValidator createUserRequestValidator;
  private final UserEventPublisher userEventPublisher;

  public User createUser(CreateUserRequest request) {
    createUserRequestValidator.validate(request);
    User savedUser = this.userRepository.save(request);

    userEventPublisher.userCreated(savedUser);
    return savedUser;
  }
}
