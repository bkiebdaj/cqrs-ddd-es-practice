package org.bkiebdaj.cqrsexample.core.event;

public interface EventDispatcher {
    void dispatchEvent(Event event);
}
