package org.bkiebdaj.cqrsexample.core.api;

public interface EventHandler<T extends Event> {
    void handle(T event);
}
