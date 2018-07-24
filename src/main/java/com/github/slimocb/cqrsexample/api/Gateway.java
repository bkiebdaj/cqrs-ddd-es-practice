package com.github.slimocb.cqrsexample.api;

public interface Gateway {

    void publishCommand(Command command);

    void publishEvent(Event event);
}
