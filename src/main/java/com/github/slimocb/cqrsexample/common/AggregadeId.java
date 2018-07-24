package com.github.slimocb.cqrsexample.common;

import lombok.Value;

import java.util.UUID;

@Value
public class AggregadeId {
    UUID id;

    private AggregadeId(UUID uuid) {
        this.id = uuid;
    }

    public static AggregadeId create() {
        return new AggregadeId(UUID.randomUUID());
    }

    public static AggregadeId of(String id) {
        return new AggregadeId(UUID.fromString(id));
    }
}
