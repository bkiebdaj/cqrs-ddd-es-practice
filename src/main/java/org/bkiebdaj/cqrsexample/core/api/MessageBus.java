package org.bkiebdaj.cqrsexample.core.api;

public interface MessageBus<T> {
    void publish(T message);
}
