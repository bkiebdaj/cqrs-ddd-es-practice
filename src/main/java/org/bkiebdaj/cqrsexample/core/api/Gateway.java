package org.bkiebdaj.cqrsexample.core.api;

public interface Gateway {

    void publishCommand(Command command);

    void publishEvent(EventPayload event);
}
