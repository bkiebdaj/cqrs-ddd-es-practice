package org.bkiebdaj.cqrsexample.domain.event;

import lombok.Value;
import org.bkiebdaj.cqrsexample.api.EventPayload;
import org.bkiebdaj.cqrsexample.common.AggregadeId;

@Value
public class EmailSentToUser implements EventPayload {
    AggregadeId aggregadeId;
    String email;
}
