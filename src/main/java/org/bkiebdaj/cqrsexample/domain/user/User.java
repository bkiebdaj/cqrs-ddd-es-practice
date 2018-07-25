package org.bkiebdaj.cqrsexample.domain.user;

import lombok.Value;
import org.bkiebdaj.cqrsexample.core.common.AggregadeId;

@Value
public class User {
    AggregadeId id;
    AggregadeId accountId;
    String login;
    String email;
}
