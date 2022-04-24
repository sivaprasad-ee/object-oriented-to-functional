package com.sivalabs.usermanagement.domain.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public void publish(UserCreatedEvent event) {
        //Publishing to in-memory Spring eventbus for sake of simplicity.
        //This could send message to Kafka/RabbitMQ etc.
        eventPublisher.publishEvent(event);
    }
}
