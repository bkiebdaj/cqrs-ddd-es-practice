package org.bkiebdaj.cqrsexample.domain.event;

import lombok.Value;
import org.bkiebdaj.cqrsexample.api.EventPayload;
import org.bkiebdaj.cqrsexample.common.AggregadeId;

@Value
public class UserCreated implements EventPayload {
    AggregadeId aggregadeId;
}
