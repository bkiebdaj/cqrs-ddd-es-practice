package org.bkiebdaj.cqrsexample.domain.account;

import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.aggreagate.AggregateRoot;
import org.bkiebdaj.cqrsexample.core.aggreagate.ApplyEvent;
import org.bkiebdaj.cqrsexample.domain.event.AccountCreated;
import org.bkiebdaj.cqrsexample.domain.event.AccountMoneyAmountDecreased;
import org.bkiebdaj.cqrsexample.domain.event.AccountMoneyAmountIncreased;

import java.math.BigDecimal;

@Slf4j
public class Account extends AggregateRoot {

    private String accountNumber;
    private BigDecimal cashAmount;

    @ApplyEvent
    public void on(AccountCreated event) {
        this.accountNumber = event.getAccountNumber();
        this.cashAmount = BigDecimal.ZERO;
    }

    @ApplyEvent
    public void on(AccountMoneyAmountIncreased event) {
        this.cashAmount = this.cashAmount.add(event.getAmount());
    }

    @ApplyEvent
    public void on(AccountMoneyAmountDecreased event) {
        this.cashAmount = this.cashAmount.subtract(event.getAmount());
    }

    public void createAccount() {
        apply(new AccountCreated(getAggregateId(), AccountNumberGenerator.generate()));
    }

    public void payInto(BigDecimal payIntoAmount) {
        apply(new AccountMoneyAmountIncreased(getAggregateId(), payIntoAmount));
    }

    public void withdraw(BigDecimal withdrawAmount) {
        if (withdrawAmount.compareTo(this.cashAmount) > 0) {
            throw new IllegalStateException("Not enough money on account for transaction: " + this.cashAmount + " but given: " + withdrawAmount);
        }
        apply(new AccountMoneyAmountDecreased(getAggregateId(), withdrawAmount));
    }
}
