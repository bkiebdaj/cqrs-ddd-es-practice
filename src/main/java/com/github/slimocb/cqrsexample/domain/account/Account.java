package com.github.slimocb.cqrsexample.domain.account;

import com.github.slimocb.cqrsexample.api.Gateway;
import com.github.slimocb.cqrsexample.common.AggregadeId;
import com.github.slimocb.cqrsexample.common.Event;
import com.github.slimocb.cqrsexample.domain.event.AccountCreated;
import com.github.slimocb.cqrsexample.domain.event.AccountMoneyAmountDecreased;
import com.github.slimocb.cqrsexample.domain.event.AccountMoneyAmountIncreased;
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
        AccountCreated event = new AccountCreated(aggregadeId, AccountNumberGenerator.generate());
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
        AccountMoneyAmountIncreased event = new AccountMoneyAmountIncreased(this.aggregadeId, amount);
        handleEvent(event);
        gateway.publishEvent(event);
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(this.cashAmount) > 0) {
            throw new IllegalStateException("Not enough money on account for transaction: " + this.cashAmount + " but given: " + amount);
        }
        AccountMoneyAmountDecreased event = new AccountMoneyAmountDecreased(this.aggregadeId, amount);
        handleEvent(event);
        gateway.publishEvent(event);
    }
}
