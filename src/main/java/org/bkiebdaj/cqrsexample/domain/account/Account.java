package org.bkiebdaj.cqrsexample.domain.account;

import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.aggreagate.AggregateRoot;
import org.bkiebdaj.cqrsexample.core.aggreagate.ApplyEvent;
import org.bkiebdaj.cqrsexample.core.common.AggregadeId;
import org.bkiebdaj.cqrsexample.domain.event.AccountCreatedEvent;
import org.bkiebdaj.cqrsexample.domain.event.AccountMoneyAmountDecreasedEvent;
import org.bkiebdaj.cqrsexample.domain.event.AccountMoneyAmountIncreasedEvent;
import org.bkiebdaj.cqrsexample.domain.event.payload.AccountCreated;
import org.bkiebdaj.cqrsexample.domain.event.payload.AccountMoneyAmountDecreased;
import org.bkiebdaj.cqrsexample.domain.event.payload.AccountMoneyAmountIncreased;

import java.math.BigDecimal;

@Slf4j
public class Account extends AggregateRoot {

    private String accountNumber;
    private BigDecimal cashAmount;

    Account(AggregadeId aggregadeId) {
        super(aggregadeId);
    }

    @ApplyEvent
    public void on(AccountCreatedEvent event) {
        this.accountNumber = event.getPayload().getAccountNumber();
        this.cashAmount = BigDecimal.ZERO;
    }

    @ApplyEvent
    public void on(AccountMoneyAmountIncreasedEvent event) {
        this.cashAmount = this.cashAmount.add(event.getPayload().getAmount());
    }

    @ApplyEvent
    public void on(AccountMoneyAmountDecreasedEvent event) {
        this.cashAmount = this.cashAmount.subtract(event.getPayload().getAmount());
    }

    public void createAccount() {
        apply(new AccountCreatedEvent(getAggregadeId(), new AccountCreated(AccountNumberGenerator.generate())));
    }

    public void payInto(BigDecimal payIntoAmount) {
        apply(new AccountMoneyAmountIncreasedEvent(getAggregadeId(), new AccountMoneyAmountIncreased(payIntoAmount)));
    }

    public void withdraw(BigDecimal withdrawAmount) {
        if (withdrawAmount.compareTo(this.cashAmount) > 0) {
            throw new IllegalStateException("Not enough money on account for transaction: " + this.cashAmount + " but given: " + withdrawAmount);
        }
        apply(new AccountMoneyAmountDecreasedEvent(getAggregadeId(), new AccountMoneyAmountDecreased(withdrawAmount)));
    }
}
