package org.bkiebdaj.cqrsexample.domain.event;

import org.bkiebdaj.cqrsexample.core.common.AggregadeId;
import org.bkiebdaj.cqrsexample.core.event.Event;
import org.bkiebdaj.cqrsexample.domain.event.payload.AccountCreated;

import java.time.LocalDateTime;

public class AccountCreatedEvent extends Event<AccountCreated> {
    public AccountCreatedEvent(AggregadeId aggregadeId, int version, LocalDateTime createTime, AccountCreated payload) {
        super(aggregadeId, version, createTime, payload);
    }
}
