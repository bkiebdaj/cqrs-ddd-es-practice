package com.github.slimocb.cqrsexample.api;

public interface MessageBus<T> {
    void publish(T message);
}
