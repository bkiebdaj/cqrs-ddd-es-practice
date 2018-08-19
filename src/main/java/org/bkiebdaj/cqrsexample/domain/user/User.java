package org.bkiebdaj.cqrsexample.domain.user;

import lombok.Value;
import org.bkiebdaj.cqrsexample.core.common.AggregateId;

@Value
public class User {
    AggregateId id;
    AggregateId accountId;
    String login;
    String email;
}
