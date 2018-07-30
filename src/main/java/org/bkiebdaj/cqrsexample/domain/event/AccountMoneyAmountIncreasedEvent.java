package org.bkiebdaj.cqrsexample.domain.event;

import org.bkiebdaj.cqrsexample.core.common.AggregadeId;
import org.bkiebdaj.cqrsexample.core.event.Event;
import org.bkiebdaj.cqrsexample.domain.event.payload.AccountMoneyAmountIncreased;

public class AccountMoneyAmountIncreasedEvent extends Event<AccountMoneyAmountIncreased> {
    public AccountMoneyAmountIncreasedEvent(AggregadeId aggregadeId, AccountMoneyAmountIncreased payload) {
        super(aggregadeId, payload);
    }
}
