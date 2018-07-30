package org.bkiebdaj.cqrsexample.domain.event;

import org.bkiebdaj.cqrsexample.core.common.AggregadeId;
import org.bkiebdaj.cqrsexample.core.event.Event;
import org.bkiebdaj.cqrsexample.domain.event.payload.UserAssignedToAccount;

import java.time.LocalDateTime;

public class UserAssignedToAccountEvent extends Event<UserAssignedToAccount> {

    public UserAssignedToAccountEvent(AggregadeId aggregadeId, int version, LocalDateTime createTime, UserAssignedToAccount payload) {
        super(aggregadeId, version, createTime, payload);
    }
}
