package org.bkiebdaj.cqrsexample.core.common;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Value;

import java.util.UUID;

@Value
public class AggregateId {
    UUID id;

    private AggregateId(UUID uuid) {
        this.id = uuid;
    }

    public static AggregateId create() {
        return new AggregateId(UUID.randomUUID());
    }

    public static AggregateId of(String id) {
        return new AggregateId(UUID.fromString(id));
    }

    @JsonValue
    public UUID getId() {
        return this.id;
    }
}
