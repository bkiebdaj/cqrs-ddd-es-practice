package com.github.slimocb.cqrsexample.domain.command;

import com.github.slimocb.cqrsexample.api.Command;
import com.github.slimocb.cqrsexample.common.AggregadeId;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class WithdrawFromAccountCommand implements Command {
    AggregadeId accountId;
    BigDecimal amount;
}
