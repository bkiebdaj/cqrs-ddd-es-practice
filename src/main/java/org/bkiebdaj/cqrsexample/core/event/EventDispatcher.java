package org.bkiebdaj.cqrsexample.core.event;

public interface EventDispatcher {
    void dispatchEvent(EventEntity event);
}
