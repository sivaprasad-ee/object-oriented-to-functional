package com.sivalabs.usermanagement.connectors.userrepository;

import com.sivalabs.usermanagement.entities.User;
import com.sivalabs.usermanagement.domain.UserRepository;
import com.sivalabs.usermanagement.domain.registration.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DbUserRepository implements UserRepository {

  private final JpaUserPersistence userPersistence;

  @Override
  public boolean existsByEmail(String email) {
    return userPersistence.existsByEmail(email);
  }

  @Override
  public User save(CreateUserRequest user) {
    return userPersistence.save(new JpaUser(null, user.getName(), user.getEmail(), user.getPhone()));
  }

  @Override
  public void deleteAllInBatch() {
    userPersistence.deleteAllInBatch();
  }
}
