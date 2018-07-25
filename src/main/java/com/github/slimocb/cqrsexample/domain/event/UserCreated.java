package com.github.slimocb.cqrsexample.domain.event;

import com.github.slimocb.cqrsexample.api.EventPayload;
import com.github.slimocb.cqrsexample.common.AggregadeId;
import lombok.Value;

@Value
public class UserCreated implements EventPayload {
    AggregadeId aggregadeId;
}
