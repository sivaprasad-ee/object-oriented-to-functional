package com.sivalabs.usermanagement.connectors.userrepository;

import com.sivalabs.usermanagement.domain.UserRepository;
import com.sivalabs.usermanagement.domain.registration.CreateUserRequest;
import com.sivalabs.usermanagement.entities.User;
import com.sivalabs.usermanagement.entities.exceptions.UserEmailExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DbUserRepository implements UserRepository {

  private final JpaUserPersistence userPersistence;

  @NotNull
  @Override
  public User save(@NotNull final CreateUserRequest user) {
    if (userPersistence.existsByEmail(user.getEmail())) {
      throw new UserEmailExistsException(user.getEmail());
    }
    return userPersistence
        .save(new JpaUser(null, user.getName(), user.getEmail(), user.getPhone()));
  }

  @Override
  public void deleteAllInBatch() {
    userPersistence.deleteAllInBatch();
  }
}
