package com.sivalabs.usermanagement.connectors.events;

import com.sivalabs.usermanagement.connectors.events.model.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Ideally this listener would be part of some other service. Added here to ensure message can be
 * consumed and processed.
 */
@Component
@Slf4j
public class SpringUserEventListener {

  @EventListener
  public void handleUserCreatedEvent(@NotNull final UserCreatedEvent event) {
    log.info("Welcome aboard {}!", event.getName());
  }
}
