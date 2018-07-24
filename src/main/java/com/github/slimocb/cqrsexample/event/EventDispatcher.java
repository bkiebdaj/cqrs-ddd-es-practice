package com.github.slimocb.cqrsexample.event;

import com.github.slimocb.cqrsexample.api.Event;

public interface EventDispatcher {
    void dispatchEvent(Event event);
}
