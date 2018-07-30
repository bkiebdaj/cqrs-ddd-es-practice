package org.bkiebdaj.cqrsexample.core.api;

import org.bkiebdaj.cqrsexample.core.event.Event;

public interface EventHandler<T extends Event> {
    void handle(T event);
}
