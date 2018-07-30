package org.bkiebdaj.cqrsexample.domain.event;

import org.bkiebdaj.cqrsexample.core.common.AggregadeId;
import org.bkiebdaj.cqrsexample.core.event.Event;
import org.bkiebdaj.cqrsexample.domain.event.payload.AccountMoneyAmountIncreased;

import java.time.LocalDateTime;

public class AccountMoneyAmountIncreasedEvent extends Event<AccountMoneyAmountIncreased> {
    public AccountMoneyAmountIncreasedEvent(AggregadeId aggregadeId, int version, LocalDateTime createTime, AccountMoneyAmountIncreased payload) {
        super(aggregadeId, version, createTime, payload);
    }
}
