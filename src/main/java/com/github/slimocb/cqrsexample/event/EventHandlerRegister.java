package com.github.slimocb.cqrsexample.event;

import com.github.slimocb.cqrsexample.api.Event;
import com.github.slimocb.cqrsexample.api.EventHandler;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
class EventHandlerRegister {
    private final Map<Class<? extends Event>, EventHandler> handlers = new HashMap<>();

    EventHandler get(Event event) {
        return handlers.get(event.getClass());
    }

    void registerHandler(EventHandler eventHandler) {
        Class<? extends Event> handledEvent = findHandledEvent(eventHandler);

        handlers.put(handledEvent, eventHandler);

        log.info("Successfully registered event handler: {}", eventHandler.getClass().getSimpleName());
    }

    private Class<? extends Event> findHandledEvent(EventHandler eventHandler) {
        Method[] methods = eventHandler.getClass().getMethods();

        return Stream.of(methods)
                .map(Method::getParameterTypes)
                .flatMap(Stream::of)
                .filter(x -> !x.isInterface())
                .map(x -> (Class<? extends Event>) x)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Can't determine handler type: " + eventHandler.getClass().getSimpleName()));
    }
}
