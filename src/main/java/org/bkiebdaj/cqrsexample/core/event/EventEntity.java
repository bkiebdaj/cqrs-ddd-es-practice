package org.bkiebdaj.cqrsexample.core.event;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.bkiebdaj.cqrsexample.core.common.AggregateId;

import java.time.LocalDateTime;

@ToString
@Getter
@Builder
public class EventEntity {
    private final AggregateId aggregateId;
    @Builder.Default
    private final int version = 0;
    @Builder.Default
    private final LocalDateTime createTime = LocalDateTime.now();

    private final String aggregateType;
    private final String eventType;
    private final String payload;
}
