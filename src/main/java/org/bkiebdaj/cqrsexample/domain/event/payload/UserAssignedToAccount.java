package org.bkiebdaj.cqrsexample.domain.event.payload;

import lombok.Value;
import org.bkiebdaj.cqrsexample.core.api.EventPayload;
import org.bkiebdaj.cqrsexample.core.common.AggregadeId;

@Value
public class UserAssignedToAccount implements EventPayload {
    AggregadeId aggregadeId;
}
