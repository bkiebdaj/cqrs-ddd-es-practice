package com.github.slimocb.cqrsexample.domain.event;

import com.github.slimocb.cqrsexample.api.Event;
import com.github.slimocb.cqrsexample.common.AggregadeId;
import lombok.Value;

@Value
public class AccountCreatedEvent implements Event {
    AggregadeId aggregadeId;
    String accountNumber;
}
