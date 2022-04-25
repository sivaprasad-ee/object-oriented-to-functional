package com.sivalabs.usermanagement.domain;

import com.sivalabs.usermanagement.domain.events.UserCreatedEvent;

public interface UserEventPublisher {
    void publish(UserCreatedEvent event);
}
