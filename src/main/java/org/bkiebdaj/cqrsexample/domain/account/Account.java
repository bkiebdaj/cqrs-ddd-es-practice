package org.bkiebdaj.cqrsexample.domain.account;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.common.AggregadeId;
import org.bkiebdaj.cqrsexample.core.event.Event;
import org.bkiebdaj.cqrsexample.domain.command.CreateAccountCommand;
import org.bkiebdaj.cqrsexample.domain.command.PayIntoAccountCommand;
import org.bkiebdaj.cqrsexample.domain.command.WithdrawFromAccountCommand;
import org.bkiebdaj.cqrsexample.domain.event.AccountCreatedEvent;
import org.bkiebdaj.cqrsexample.domain.event.AccountMoneyAmountDecreasedEvent;
import org.bkiebdaj.cqrsexample.domain.event.AccountMoneyAmountIncreasedEvent;
import org.bkiebdaj.cqrsexample.domain.event.payload.AccountCreated;
import org.bkiebdaj.cqrsexample.domain.event.payload.AccountMoneyAmountDecreased;
import org.bkiebdaj.cqrsexample.domain.event.payload.AccountMoneyAmountIncreased;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Slf4j
public class Account {
    @Getter
    private AggregadeId aggregadeId;

    private String accountNumber;
    private BigDecimal cashAmount;

    Account(AggregadeId aggregadeId) {
        this.aggregadeId = aggregadeId;
    }

    public void apply(Event event) {
        if (AccountCreatedEvent.class.isAssignableFrom(event.getClass())) {
            apply(AccountCreated.class.cast(event.getPayload()));
        } else if (AccountMoneyAmountIncreasedEvent.class.isAssignableFrom(event.getClass())) {
            apply(AccountMoneyAmountIncreased.class.cast(event.getPayload()));
        } else if (AccountMoneyAmountDecreasedEvent.class.isAssignableFrom(event.getClass())) {
            apply(AccountMoneyAmountDecreased.class.cast(event.getPayload()));
        }
    }

    private void apply(AccountCreated accountCreated) {
        this.aggregadeId = accountCreated.getAggregadeId();
        this.accountNumber = accountCreated.getAccountNumber();
        this.cashAmount = BigDecimal.ZERO;
    }

    private void apply(AccountMoneyAmountIncreased event) {
        this.cashAmount = this.cashAmount.add(event.getAmount());
    }

    private void apply(AccountMoneyAmountDecreased event) {
        this.cashAmount = this.cashAmount.subtract(event.getAmount());
    }

    void replay(List<Event> events) {
        events.forEach(this::apply);
    }

    public List<Event> handle(CreateAccountCommand createAccountCommand) {
        Event event = new AccountCreatedEvent(aggregadeId, new AccountCreated(aggregadeId, AccountNumberGenerator.generate()));
        return Collections.singletonList(event);
    }

    public List<Event> handle(PayIntoAccountCommand command) {
        Event event = new AccountMoneyAmountIncreasedEvent(aggregadeId, new AccountMoneyAmountIncreased(aggregadeId, command.getAmount()));
        return Collections.singletonList(event);
    }

    public List<Event> handle(WithdrawFromAccountCommand command) {
        if (command.getAmount().compareTo(this.cashAmount) > 0) {
            throw new IllegalStateException("Not enough money on account for transaction: " + this.cashAmount + " but given: " + command.getAmount());
        }
        Event event = new AccountMoneyAmountDecreasedEvent(aggregadeId, new AccountMoneyAmountDecreased(aggregadeId, command.getAmount()));
        return Collections.singletonList(event);
    }
}
