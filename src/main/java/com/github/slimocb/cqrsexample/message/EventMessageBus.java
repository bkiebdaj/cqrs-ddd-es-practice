package com.github.slimocb.cqrsexample.message;

import com.github.slimocb.cqrsexample.api.EventPayload;
import com.github.slimocb.cqrsexample.event.store.EventStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventMessageBus extends SimpleMessageBus<EventPayload> {

    private final EventStore eventStore;

    @Override
    public void publish(EventPayload message) {
        eventStore.save(message);
        super.publish(message);
    }
}
