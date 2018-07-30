package org.bkiebdaj.cqrsexample.domain.event;

import org.bkiebdaj.cqrsexample.core.common.AggregadeId;
import org.bkiebdaj.cqrsexample.core.event.Event;
import org.bkiebdaj.cqrsexample.domain.event.payload.AccountMoneyAmountDecreased;

public class AccountMoneyAmountDecreasedEvent extends Event<AccountMoneyAmountDecreased> {
    public AccountMoneyAmountDecreasedEvent(AggregadeId aggregadeId, AccountMoneyAmountDecreased payload) {
        super(aggregadeId, payload);
    }
}
