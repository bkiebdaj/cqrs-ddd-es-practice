package org.bkiebdaj.cqrsexample.core.aggreagate;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.aggreagate.exception.AggregateException;
import org.bkiebdaj.cqrsexample.core.common.AggregadeId;
import org.bkiebdaj.cqrsexample.core.event.Event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
abstract public class AggregateRoot {

    private static final AggregateMethodReferenceCache eventHandlersCache = new AggregateMethodReferenceCache(ApplyEvent.class);

    @Getter
    private final List<Event> freshEvents = new ArrayList<>();

    @Getter
    private final AggregadeId aggregadeId;

    public AggregateRoot(AggregadeId aggregadeId) {
        this.aggregadeId = aggregadeId;
    }

    //Should be package scope when abstract aggregate repository will be available.
    public void apply(List<Event> events) {
        events.forEach(this::apply);
    }

    protected void apply(Event... events) {
        Stream.of(events).forEach(this::applyFresh);
    }

    private void applyFresh(Event event) {
        apply(event);
        freshEvents.add(event);
    }

    private void apply(Event event) {
        log.info("Aggregate root apply event: {}", event);
        fireHandleMethod(event, eventHandlersCache.getMethod(getClass(), event.getClass()));
    }

    private void fireHandleMethod(Object event, Method method) {
        try {
            method.invoke(this, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AggregateException("Unexpected problem while invoking method", e);
        }
    }

    public void markAsUpdated() {
        freshEvents.clear();
    }
}
