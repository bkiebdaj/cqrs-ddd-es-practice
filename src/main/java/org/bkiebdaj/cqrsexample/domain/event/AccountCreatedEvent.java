package org.bkiebdaj.cqrsexample.domain.event;

import org.bkiebdaj.cqrsexample.core.common.AggregadeId;
import org.bkiebdaj.cqrsexample.core.event.Event;
import org.bkiebdaj.cqrsexample.domain.event.payload.AccountCreated;

public class AccountCreatedEvent extends Event<AccountCreated> {
    public AccountCreatedEvent(AggregadeId aggregadeId, AccountCreated payload) {
        super(aggregadeId, payload);
    }
}
