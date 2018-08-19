package org.bkiebdaj.cqrsexample.domain.command;

import lombok.Value;
import org.bkiebdaj.cqrsexample.core.api.Command;
import org.bkiebdaj.cqrsexample.core.common.AggregateId;

import java.math.BigDecimal;

@Value
public class WithdrawFromAccountCommand implements Command {
    AggregateId accountId;
    BigDecimal amount;
}
