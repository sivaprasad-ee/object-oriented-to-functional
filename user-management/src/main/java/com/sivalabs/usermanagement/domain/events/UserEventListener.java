package com.sivalabs.usermanagement.domain.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserEventListener {

    @EventListener
    public void handleUserCreatedEvent(UserCreatedEvent event) {
      log.info("Welcome aboard {}!", event.getName());
    }
}
