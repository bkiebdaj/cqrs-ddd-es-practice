package org.bkiebdaj.cqrsexample.domain.event.payload;

import lombok.Value;
import org.bkiebdaj.cqrsexample.core.api.EventPayload;
import org.bkiebdaj.cqrsexample.core.common.AggregadeId;

@Value
public class UserCreated implements EventPayload {
    AggregadeId aggregadeId;
}
