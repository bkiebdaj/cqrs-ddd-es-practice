package org.bkiebdaj.cqrsexample.domain.event;

import lombok.Value;
import org.bkiebdaj.cqrsexample.core.api.EventPayload;
import org.bkiebdaj.cqrsexample.core.common.AggregadeId;

@Value
public class AccountCreated implements EventPayload {
    AggregadeId aggregadeId;
    String accountNumber;
}
