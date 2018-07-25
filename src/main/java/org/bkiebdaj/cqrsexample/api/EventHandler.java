package org.bkiebdaj.cqrsexample.api;

public interface EventHandler<T extends EventPayload> {
    void handle(T event);
}
