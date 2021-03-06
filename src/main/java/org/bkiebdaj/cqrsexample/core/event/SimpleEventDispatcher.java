package org.bkiebdaj.cqrsexample.core.event;

import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.api.Event;
import org.bkiebdaj.cqrsexample.core.api.EventHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class SimpleEventDispatcher implements EventDispatcher {

    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    private EventHandlerRegister eventHandlerRegister = new EventHandlerRegister();

    public SimpleEventDispatcher(List<EventHandler> eventHandlers) {
        eventHandlers.forEach(eventHandlerRegister::registerHandler);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void dispatchEvent(EventEntity event) {
        log.info("Dispatch event: {}", name(event));
        Event deserializedEvent = EventDeserializer.deserialize(event);
        EventHandler eventHandler = eventHandlerRegister.get(deserializedEvent);
        if (eventHandler == null) {
            log.debug("No present handlers for event: {}", name(event));
            return;
        }
        log.info("Found event handler: {} --> {}", name(event), name(eventHandler));
        executor.execute(() -> eventHandler.handle(deserializedEvent));
    }

    private String name(Object object) {
        return object.getClass().getSimpleName();
    }
}
