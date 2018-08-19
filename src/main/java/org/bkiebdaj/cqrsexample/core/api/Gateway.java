package org.bkiebdaj.cqrsexample.core.api;

import org.bkiebdaj.cqrsexample.core.event.EventEntity;

public interface Gateway {

    void publishCommand(Command command);

    void publishEvent(EventEntity event);
}
