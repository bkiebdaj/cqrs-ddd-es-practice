package org.bkiebdaj.cqrsexample.api;

public interface Gateway {

    void publishCommand(Command command);

    void publishEvent(EventPayload event);
}
