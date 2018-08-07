package org.bkiebdaj.cqrsexample.domain.event.payload;

import lombok.Value;
import org.bkiebdaj.cqrsexample.core.api.EventPayload;

@Value
public class AccountCreated implements EventPayload {
    String accountNumber;
}
