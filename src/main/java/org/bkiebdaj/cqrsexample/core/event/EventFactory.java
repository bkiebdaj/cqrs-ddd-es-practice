package org.bkiebdaj.cqrsexample.core.event;

import org.bkiebdaj.cqrsexample.core.api.EventPayload;
import org.bkiebdaj.cqrsexample.core.common.AggregadeId;

import java.time.LocalDateTime;

public class EventFactory {

    public static Event create(EventPayload eventPayload) {
        return create(eventPayload, 0);
    }

    public static Event create(EventPayload eventPayload, int version) {
        return new Event(AggregadeId.create(), version, LocalDateTime.now(), eventPayload);
    }
}
