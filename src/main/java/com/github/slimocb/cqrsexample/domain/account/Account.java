package com.github.slimocb.cqrsexample.domain.account;

import com.github.slimocb.cqrsexample.api.Event;
import com.github.slimocb.cqrsexample.api.Gateway;
import com.github.slimocb.cqrsexample.common.AggregadeId;
import com.github.slimocb.cqrsexample.domain.event.AccountCreatedEvent;
import com.github.slimocb.cqrsexample.domain.event.AccountMoneyAmountDecreasedEvent;
import com.github.slimocb.cqrsexample.domain.event.AccountMoneyAmountIncreasedEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

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
        AccountCreatedEvent event = new AccountCreatedEvent(aggregadeId, AccountNumberGenerator.generate());

        handleEvent(event);

        gateway.publishEvent(event);
    }

    Account(Gateway gateway) {
        this.gateway = gateway;
    }

    private void handleEvent(Event event) {
        if (AccountCreatedEvent.class.isAssignableFrom(event.getClass())) {
            handleEvent(AccountCreatedEvent.class.cast(event));
        } else if (AccountMoneyAmountIncreasedEvent.class.isAssignableFrom(event.getClass())) {
            handleEvent(AccountMoneyAmountIncreasedEvent.class.cast(event));
        } else if (AccountMoneyAmountDecreasedEvent.class.isAssignableFrom(event.getClass())) {
            handleEvent(AccountMoneyAmountDecreasedEvent.class.cast(event));
        }
    }

    private void handleEvent(AccountCreatedEvent accountCreatedEvent) {
        this.aggregadeId = accountCreatedEvent.getAggregadeId();
        this.accountNumber = accountCreatedEvent.getAccountNumber();
        this.cashAmount = BigDecimal.ZERO;
    }

    private void handleEvent(AccountMoneyAmountIncreasedEvent event) {
        this.cashAmount = this.cashAmount.add(event.getAmount());
    }

    private void handleEvent(AccountMoneyAmountDecreasedEvent event) {
        this.cashAmount = this.cashAmount.subtract(event.getAmount());
    }

    void replay(List<Event> events) {
        events.forEach(this::handleEvent);
    }

    public void payInto(BigDecimal amount) {
        AccountMoneyAmountIncreasedEvent event = new AccountMoneyAmountIncreasedEvent(this.aggregadeId, amount);
        handleEvent(event);
        gateway.publishEvent(event);
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(this.cashAmount) > 0) {
            throw new IllegalStateException("Not enough money on account for transaction: " + this.cashAmount + " but given: " + amount);
        }
        AccountMoneyAmountDecreasedEvent event = new AccountMoneyAmountDecreasedEvent(this.aggregadeId, amount);
        handleEvent(event);
        gateway.publishEvent(event);
    }
}
