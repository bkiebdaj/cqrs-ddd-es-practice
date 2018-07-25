package org.bkiebdaj.cqrsexample.domain.event;

import lombok.Value;
import org.bkiebdaj.cqrsexample.core.api.EventPayload;
import org.bkiebdaj.cqrsexample.core.common.AggregadeId;

import java.math.BigDecimal;

@Value
public class AccountMoneyAmountDecreased implements EventPayload {
    AggregadeId aggregadeId;
    BigDecimal amount;
}
