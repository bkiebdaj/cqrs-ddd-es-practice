package org.bkiebdaj.cqrsexample.domain.event;

import org.bkiebdaj.cqrsexample.core.common.AggregadeId;
import org.bkiebdaj.cqrsexample.core.event.Event;
import org.bkiebdaj.cqrsexample.domain.event.payload.EmailSentToUser;

import java.time.LocalDateTime;

public class EmailSendToUserEvent extends Event<EmailSentToUser> {
    public EmailSendToUserEvent(AggregadeId aggregadeId, int version, LocalDateTime createTime, EmailSentToUser payload) {
        super(aggregadeId, version, createTime, payload);
    }
}
