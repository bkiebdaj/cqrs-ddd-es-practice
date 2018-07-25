package org.bkiebdaj.cqrsexample.common;

import com.fasterxml.jackson.annotation.JsonValue;
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

    @JsonValue
    public UUID getId() {
        return this.id;
    }
}
