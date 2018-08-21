package org.bkiebdaj.cqrsexample.core.aggreagate;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.aggreagate.exception.AggregateException;
import org.bkiebdaj.cqrsexample.core.api.Event;
import org.bkiebdaj.cqrsexample.core.common.AggregateId;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
abstract public class AggregateRoot {

    private static final AggregateMethodReferenceCache eventHandlersCache = new AggregateMethodReferenceCache(ApplyEvent.class);

    @Getter
    private final List<Event> uncommittedEvents = new ArrayList<>();

    @Getter
    private AggregateId aggregateId;

    @Getter
    private int version = 0;

    void setAggregateId(AggregateId aggregateId) {
        if (this.aggregateId != null) {
            throw new AggregateException("Aggregate is already set!!!");
        }
        this.aggregateId = aggregateId;
    }

    void replay(List<Event> events) {
        version += events.size();
        events.forEach(this::apply);
    }

    protected void apply(Event... events) {
        Stream.of(events).forEach(this::applyFresh);
    }

    private void applyFresh(Event event) {
        apply(event);
        uncommittedEvents.add(event);
    }

    private void apply(Event event) {
        log.info("Aggregate root replay event: {}", event);
        fireHandleMethod(event, eventHandlersCache.getMethod(getClass(), event.getClass()));
    }

    private void fireHandleMethod(Object event, Method method) {
        try {
            method.invoke(this, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AggregateException("Unexpected problem while invoking method", e);
        }
    }

    void markAsCommitted(int updatedVersion) {
        version = updatedVersion;
        uncommittedEvents.clear();
    }
}
