package org.bkiebdaj.cqrsexample.core.api;

import org.bkiebdaj.cqrsexample.core.event.Event;

public interface Gateway {

    void publishCommand(Command command);

    void publishEvent(Event event);
}
