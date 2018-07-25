package com.github.slimocb.cqrsexample.domain.event;

import com.github.slimocb.cqrsexample.api.EventPayload;
import com.github.slimocb.cqrsexample.common.AggregadeId;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class AccountMoneyAmountIncreased implements EventPayload {
    AggregadeId aggregadeId;
    BigDecimal amount;
}
