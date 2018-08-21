package org.bkiebdaj.cqrsexample.domain.account.event;

import lombok.Value;
import org.bkiebdaj.cqrsexample.core.api.Event;
import org.bkiebdaj.cqrsexample.core.common.AggregateId;

@Value
public class AccountCreated implements Event {
    AggregateId aggregateId;
    String accountNumber;
}
