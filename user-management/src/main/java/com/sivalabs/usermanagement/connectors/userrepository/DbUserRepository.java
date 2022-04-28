package com.sivalabs.usermanagement.connectors.userrepository;

import com.sivalabs.usermanagement.entities.User;
import com.sivalabs.usermanagement.domain.UserRepository;
import com.sivalabs.usermanagement.domain.registration.CreateUserRequest;
import com.sivalabs.usermanagement.entities.exceptions.ResourceAlreadyExistsException;
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
  public User save(CreateUserRequest user) {
    if (userPersistence.existsByEmail(user.getEmail())) {
      throw new ResourceAlreadyExistsException(
          "User already registered with email " + user.getEmail());
    }
    return userPersistence.save(new JpaUser(null, user.getName(), user.getEmail(), user.getPhone()));
  }

  @Override
  public void deleteAllInBatch() {
    userPersistence.deleteAllInBatch();
  }
}
