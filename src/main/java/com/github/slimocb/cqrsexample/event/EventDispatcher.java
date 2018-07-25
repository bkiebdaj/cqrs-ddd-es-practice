package com.github.slimocb.cqrsexample.event;

import com.github.slimocb.cqrsexample.api.EventPayload;

public interface EventDispatcher {
    void dispatchEvent(EventPayload event);
}
