package org.bkiebdaj.cqrsexample.core.event;

import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.api.EventHandler;
import org.bkiebdaj.cqrsexample.core.api.EventPayload;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
class EventHandlerRegister {
    private final Map<Class<? extends EventPayload>, EventHandler> handlers = new HashMap<>();

    EventHandler get(EventPayload event) {
        return handlers.get(event.getClass());
    }

    void registerHandler(EventHandler eventHandler) {
        Class<? extends EventPayload> handledEvent = findHandledEvent(eventHandler);

        handlers.put(handledEvent, eventHandler);

        log.info("Successfully registered event handler: {}", eventHandler.getClass().getSimpleName());
    }

    private Class<? extends EventPayload> findHandledEvent(EventHandler eventHandler) {
        Method[] methods = eventHandler.getClass().getMethods();

        return Stream.of(methods)
                .map(Method::getParameterTypes)
                .flatMap(Stream::of)
                .filter(x -> !x.isInterface())
                .map(x -> (Class<? extends EventPayload>) x)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Can't determine handler type: " + eventHandler.getClass().getSimpleName()));
    }
}
