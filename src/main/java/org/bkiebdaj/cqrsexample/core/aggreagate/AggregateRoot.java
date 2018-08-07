package org.bkiebdaj.cqrsexample.core.aggreagate;

import lombok.Getter;
import org.bkiebdaj.cqrsexample.core.aggreagate.exception.AggregateException;
import org.bkiebdaj.cqrsexample.core.common.AggregadeId;
import org.bkiebdaj.cqrsexample.core.event.Event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

abstract public class AggregateRoot {

    private static final AggregateMethodReferenceCache eventHandlersCache = new AggregateMethodReferenceCache(ApplyEvent.class);

    @Getter
    private final AggregadeId aggregadeId;

    public AggregateRoot(AggregadeId aggregadeId) {
        this.aggregadeId = aggregadeId;
    }

    public void apply(List<Event> events) {
        events.forEach(this::apply);
    }

    private void apply(Event event) {
        fireHandleMethod(event, eventHandlersCache.getMethod(getClass(), event.getClass()));
    }

    private void fireHandleMethod(Object event, Method method) {
        try {
            method.invoke(this, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AggregateException("Unexpected problem while invoking method", e);
        }
    }
}
