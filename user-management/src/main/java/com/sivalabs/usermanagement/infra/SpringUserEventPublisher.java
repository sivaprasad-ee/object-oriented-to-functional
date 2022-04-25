package com.sivalabs.usermanagement.infra;

import com.sivalabs.usermanagement.domain.UserEventPublisher;
import com.sivalabs.usermanagement.domain.events.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class SpringUserEventPublisher implements UserEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public void publish(UserCreatedEvent event) {
        //Publishing to in-memory Spring eventbus for sake of simplicity.
        //This could send message to Kafka/RabbitMQ etc.
        eventPublisher.publishEvent(event);
    }
}
