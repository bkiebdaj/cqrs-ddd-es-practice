package org.bkiebdaj.cqrsexample.api;

public interface MessageBus<T> {
    void publish(T message);
}
