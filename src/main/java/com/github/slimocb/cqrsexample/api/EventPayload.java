package com.github.slimocb.cqrsexample.api;

import com.github.slimocb.cqrsexample.common.AggregadeId;

public interface EventPayload {
    AggregadeId getAggregadeId();
}
