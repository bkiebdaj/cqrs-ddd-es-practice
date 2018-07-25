package org.bkiebdaj.cqrsexample.core.message;

import lombok.RequiredArgsConstructor;
import org.bkiebdaj.cqrsexample.core.event.Event;
import org.bkiebdaj.cqrsexample.core.event.store.EventStore;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventMessageBus extends SimpleMessageBus<Event> {

    private final EventStore eventStore;

    @Override
    public void publish(Event message) {
        eventStore.save(message);
        super.publish(message);
    }
}
