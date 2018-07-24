package com.github.slimocb.cqrsexample.api;

public interface EventHandler<T extends Event> {
    void handle(T event);
}
