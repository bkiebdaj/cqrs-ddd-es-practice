package org.bkiebdaj.cqrsexample.domain.account;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.api.Gateway;
import org.bkiebdaj.cqrsexample.core.common.AggregadeId;
import org.bkiebdaj.cqrsexample.core.event.Event;
import org.bkiebdaj.cqrsexample.core.event.EventFactory;
import org.bkiebdaj.cqrsexample.domain.event.AccountCreated;
import org.bkiebdaj.cqrsexample.domain.event.AccountMoneyAmountDecreased;
import org.bkiebdaj.cqrsexample.domain.event.AccountMoneyAmountIncreased;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
public class Account {
    private final Gateway gateway;
    @Getter
    private AggregadeId aggregadeId;

    private String accountNumber;
    private BigDecimal cashAmount;

    Account(Gateway gateway, AggregadeId aggregadeId) {
        this.gateway = gateway;
        Event event = EventFactory.create(new AccountCreated(aggregadeId, AccountNumberGenerator.generate()));
        handleEvent(event);
        gateway.publishEvent(event);
    }

    Account(Gateway gateway) {
        this.gateway = gateway;
    }

    private void handleEvent(Event event) {
        if (AccountCreated.class.isAssignableFrom(event.getType())) {
            handleEvent(AccountCreated.class.cast(event.getPayload()));
        } else if (AccountMoneyAmountIncreased.class.isAssignableFrom(event.getType())) {
            handleEvent(AccountMoneyAmountIncreased.class.cast(event.getPayload()));
        } else if (AccountMoneyAmountDecreased.class.isAssignableFrom(event.getType())) {
            handleEvent(AccountMoneyAmountDecreased.class.cast(event.getPayload()));
        }
    }

    private void handleEvent(AccountCreated accountCreated) {
        this.aggregadeId = accountCreated.getAggregadeId();
        this.accountNumber = accountCreated.getAccountNumber();
        this.cashAmount = BigDecimal.ZERO;
    }

    private void handleEvent(AccountMoneyAmountIncreased event) {
        this.cashAmount = this.cashAmount.add(event.getAmount());
    }

    private void handleEvent(AccountMoneyAmountDecreased event) {
        this.cashAmount = this.cashAmount.subtract(event.getAmount());
    }

    void replay(List<Event> events) {
        events.forEach(this::handleEvent);
    }

    public void payInto(BigDecimal amount) {
        Event event = EventFactory.create(new AccountMoneyAmountIncreased(this.aggregadeId, amount));
        handleEvent(event);
        gateway.publishEvent(event);
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(this.cashAmount) > 0) {
            throw new IllegalStateException("Not enough money on account for transaction: " + this.cashAmount + " but given: " + amount);
        }
        Event event = EventFactory.create(new AccountMoneyAmountDecreased(this.aggregadeId, amount));
        handleEvent(event);
        gateway.publishEvent(event);
    }
}
