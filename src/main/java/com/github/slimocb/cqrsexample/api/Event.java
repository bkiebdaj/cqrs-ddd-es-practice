package com.github.slimocb.cqrsexample.api;

import com.github.slimocb.cqrsexample.common.AggregadeId;

public interface Event {
    AggregadeId getAggregadeId();

    default String getName() {
        return this.getClass().getSimpleName();
    }
}
