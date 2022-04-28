package com.sivalabs.usermanagement.domain.registration;

import com.sivalabs.usermanagement.domain.UserEventPublisher;
import com.sivalabs.usermanagement.domain.UserRepository;
import com.sivalabs.usermanagement.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRegistrationService {

  private final UserRepository userRepository;
  private final CreateUserRequestValidator createUserRequestValidator;
  private final UserEventPublisher userEventPublisher;

  @NotNull
  public User createUser(@NotNull final CreateUserRequest request) {
    return userEventPublisher.userCreated(
        userRepository.save(createUserRequestValidator.validate(request)));
  }
}
