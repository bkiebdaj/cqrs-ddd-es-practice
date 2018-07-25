package org.bkiebdaj.cqrsexample.event;

import org.bkiebdaj.cqrsexample.api.EventPayload;

public interface EventDispatcher {
    void dispatchEvent(EventPayload event);
}
