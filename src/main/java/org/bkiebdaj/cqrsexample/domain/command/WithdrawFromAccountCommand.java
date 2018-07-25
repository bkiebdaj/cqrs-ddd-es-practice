package org.bkiebdaj.cqrsexample.domain.command;

import lombok.Value;
import org.bkiebdaj.cqrsexample.api.Command;
import org.bkiebdaj.cqrsexample.common.AggregadeId;

import java.math.BigDecimal;

@Value
public class WithdrawFromAccountCommand implements Command {
    AggregadeId accountId;
    BigDecimal amount;
}
