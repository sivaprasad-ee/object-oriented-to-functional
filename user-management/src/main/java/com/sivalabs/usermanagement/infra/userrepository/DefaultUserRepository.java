package com.sivalabs.usermanagement.infra.userrepository;

import com.sivalabs.usermanagement.domain.UserAlreadyExistsException;
import com.sivalabs.usermanagement.domain.User;
import com.sivalabs.usermanagement.domain.UserRepository;
import com.sivalabs.usermanagement.domain.registration.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DefaultUserRepository implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(CreateUserRequest request) {
        try {
            UserEntity entity = new UserEntity(null, request.getName(), request.getEmail(), request.getPhone());
            UserEntity savedUser = jpaUserRepository.save(entity);
            return new User(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getPhone());
        } catch (DataIntegrityViolationException e) {
            //currently only email is unique so making an assumption here
            //there should be a better way to identify which exact field/column is causing the problem
            throw new UserAlreadyExistsException("User already exists with given email");
        }
    }

    @Override
    public void deleteAll() {
        jpaUserRepository.deleteAllInBatch();
    }
}
