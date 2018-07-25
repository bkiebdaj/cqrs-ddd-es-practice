package org.bkiebdaj.cqrsexample.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;
import org.bkiebdaj.cqrsexample.api.EventPayload;

import java.time.LocalDateTime;

@ToString
@Getter
public class Event {
    private final AggregadeId aggregadeId;
    private final int version;
    private final LocalDateTime createTime;
    private final Class<? extends EventPayload> type;
    private final EventPayload payload;

    public Event(AggregadeId aggregadeId, int version, LocalDateTime createTime, EventPayload payload) {
        this.aggregadeId = aggregadeId;
        this.version = version;
        this.createTime = createTime;
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
