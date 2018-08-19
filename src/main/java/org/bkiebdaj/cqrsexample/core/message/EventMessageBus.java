package org.bkiebdaj.cqrsexample.core.message;

import lombok.RequiredArgsConstructor;
import org.bkiebdaj.cqrsexample.core.event.EventEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventMessageBus extends SimpleMessageBus<EventEntity> {
}
