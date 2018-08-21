package org.bkiebdaj.cqrsexample.domain.account.event;

import lombok.Value;
import org.bkiebdaj.cqrsexample.core.api.Event;
import org.bkiebdaj.cqrsexample.core.common.AggregateId;

import java.math.BigDecimal;

@Value
public class AccountMoneyAmountDecreased implements Event {
    AggregateId aggregateId;
    BigDecimal amount;
}
