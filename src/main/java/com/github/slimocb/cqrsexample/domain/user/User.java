package com.github.slimocb.cqrsexample.domain.user;

import com.github.slimocb.cqrsexample.common.AggregadeId;
import lombok.Value;

@Value
public class User {
    AggregadeId id;
    AggregadeId accountId;
    String login;
    String email;
}
