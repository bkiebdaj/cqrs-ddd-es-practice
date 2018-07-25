package org.bkiebdaj.cqrsexample.core.event;

import org.bkiebdaj.cqrsexample.core.api.EventPayload;

public interface EventDispatcher {
    void dispatchEvent(EventPayload event);
}
