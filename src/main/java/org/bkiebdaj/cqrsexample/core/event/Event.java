package org.bkiebdaj.cqrsexample.core.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;
import org.bkiebdaj.cqrsexample.core.api.EventPayload;
import org.bkiebdaj.cqrsexample.core.common.AggregadeId;

import java.time.LocalDateTime;

@ToString
@Getter
abstract public class Event<T extends EventPayload> {
    private final AggregadeId aggregadeId;
    private final int version;
    private final LocalDateTime createTime;
    private final Class<? extends EventPayload> type;
    private final T payload;

    public Event(AggregadeId aggregadeId, T payload) {
        this.aggregadeId = aggregadeId;
        this.version = 0;
        this.createTime = LocalDateTime.now();
        this.payload = payload;
        this.type = payload.getClass();
    }

    @JsonIgnore
    public Class<? extends EventPayload> getType() {
        return type;
    }

    public String getTypeValue() {
        return type.getSimpleName();
    }
}
