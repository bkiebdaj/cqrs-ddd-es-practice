package org.bkiebdaj.cqrsexample.domain.event.payload;

import lombok.Value;
import org.bkiebdaj.cqrsexample.core.api.EventPayload;

import java.math.BigDecimal;

@Value
public class AccountMoneyAmountIncreased implements EventPayload {
    BigDecimal amount;
}
