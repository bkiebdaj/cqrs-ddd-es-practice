package com.github.slimocb.cqrsexample.domain.event;

import com.github.slimocb.cqrsexample.api.Event;
import com.github.slimocb.cqrsexample.common.AggregadeId;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class AccountMoneyAmountIncreasedEvent implements Event {
    AggregadeId aggregadeId;
    BigDecimal amount;
}
