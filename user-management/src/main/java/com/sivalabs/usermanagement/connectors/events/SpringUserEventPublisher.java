package com.sivalabs.usermanagement.connectors.events;

import com.sivalabs.usermanagement.connectors.events.model.UserCreatedEvent;
import com.sivalabs.usermanagement.domain.UserEventPublisher;
import com.sivalabs.usermanagement.entities.User;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class SpringUserEventPublisher implements UserEventPublisher {

  private final ApplicationEventPublisher eventPublisher;

  @NotNull
  public User userCreated(@NotNull  final User savedUser) {
    //Publishing to in-memory Spring eventbus for sake of simplicity.
    //This could send message to Kafka/RabbitMQ etc.
    eventPublisher.publishEvent(
        new UserCreatedEvent(savedUser.getId(), savedUser.getName(), savedUser.getEmail(),
            savedUser.getPhone()));

    return savedUser;
  }
}
