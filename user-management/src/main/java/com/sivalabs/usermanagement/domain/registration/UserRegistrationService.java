package com.sivalabs.usermanagement.domain.registration;

import com.sivalabs.usermanagement.common.BadRequestException;
import com.sivalabs.usermanagement.domain.User;
import com.sivalabs.usermanagement.domain.UserEventPublisher;
import com.sivalabs.usermanagement.domain.UserRepository;
import com.sivalabs.usermanagement.domain.events.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserRegistrationService {
    private final UserRepository userRepository;
    private final CreateUserRequestValidator createUserRequestValidator;
    private final UserEventPublisher userEventPublisher;

    public User createUser(CreateUserRequest request) {
        createUserRequestValidator.validate(request);
        User user = new User(null, request.getName(), request.getEmail(), request.getPhone());
        User savedUser = this.userRepository.save(user);
        UserCreatedEvent event = new UserCreatedEvent(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getPhone());
        userEventPublisher.publish(event);
        return savedUser;
    }
}
