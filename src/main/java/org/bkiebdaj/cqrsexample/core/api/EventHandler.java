package org.bkiebdaj.cqrsexample.core.api;

public interface EventHandler<T extends EventPayload> {
    void handle(T event);
}
