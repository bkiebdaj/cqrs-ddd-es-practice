package org.bkiebdaj.cqrsexample.domain.account;

import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.aggreagate.AggregateRoot;
import org.bkiebdaj.cqrsexample.core.aggreagate.ApplyEvent;
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
public class Account extends AggregateRoot {

    private String accountNumber;
    private BigDecimal cashAmount;

    Account(AggregadeId aggregadeId) {
        super(aggregadeId);
    }

    @ApplyEvent
    public void apply(AccountCreatedEvent event) {
        this.accountNumber = event.getPayload().getAccountNumber();
        this.cashAmount = BigDecimal.ZERO;
    }

    @ApplyEvent
    public void apply(AccountMoneyAmountIncreasedEvent event) {
        this.cashAmount = this.cashAmount.add(event.getPayload().getAmount());
    }

    @ApplyEvent
    public void apply(AccountMoneyAmountDecreasedEvent event) {
        this.cashAmount = this.cashAmount.subtract(event.getPayload().getAmount());
    }

    public List<Event> handle(CreateAccountCommand createAccountCommand) {
        Event event = new AccountCreatedEvent(getAggregadeId(), new AccountCreated(AccountNumberGenerator.generate()));
        return Collections.singletonList(event);
    }

    public List<Event> handle(PayIntoAccountCommand command) {
        Event event = new AccountMoneyAmountIncreasedEvent(getAggregadeId(), new AccountMoneyAmountIncreased(command.getAmount()));
        return Collections.singletonList(event);
    }

    public List<Event> handle(WithdrawFromAccountCommand command) {
        if (command.getAmount().compareTo(this.cashAmount) > 0) {
            throw new IllegalStateException("Not enough money on account for transaction: " + this.cashAmount + " but given: " + command.getAmount());
        }
        Event event = new AccountMoneyAmountDecreasedEvent(getAggregadeId(), new AccountMoneyAmountDecreased(command.getAmount()));
        return Collections.singletonList(event);
    }
}
