package org.bkiebdaj.cqrsexample.domain.event;

import org.bkiebdaj.cqrsexample.core.common.AggregadeId;
import org.bkiebdaj.cqrsexample.core.event.Event;
import org.bkiebdaj.cqrsexample.domain.event.payload.UserCreated;

import java.time.LocalDateTime;

public class UserCreatedEvent extends Event<UserCreated> {
    public UserCreatedEvent(AggregadeId aggregadeId, int version, LocalDateTime createTime, UserCreated payload) {
        super(aggregadeId, version, createTime, payload);
    }
}
