package org.bkiebdaj.cqrsexample.domain.event;

import org.bkiebdaj.cqrsexample.core.common.AggregadeId;
import org.bkiebdaj.cqrsexample.core.event.Event;
import org.bkiebdaj.cqrsexample.domain.event.payload.AccountMoneyAmountDecreased;

import java.time.LocalDateTime;

public class AccountMoneyAmountDecreasedEvent extends Event<AccountMoneyAmountDecreased> {
    public AccountMoneyAmountDecreasedEvent(AggregadeId aggregadeId, int version, LocalDateTime createTime, AccountMoneyAmountDecreased payload) {
        super(aggregadeId, version, createTime, payload);
    }
}
