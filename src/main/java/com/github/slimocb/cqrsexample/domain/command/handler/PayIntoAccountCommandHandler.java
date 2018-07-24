package com.github.slimocb.cqrsexample.domain.command.handler;

import com.github.slimocb.cqrsexample.api.CommandHandler;
import com.github.slimocb.cqrsexample.api.Gateway;
import com.github.slimocb.cqrsexample.domain.account.Account;
import com.github.slimocb.cqrsexample.domain.account.AccountFactory;
import com.github.slimocb.cqrsexample.domain.command.PayIntoAccountCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PayIntoAccountCommandHandler implements CommandHandler<PayIntoAccountCommand> {

    private final Gateway gateway;
    private final AccountFactory accountFactory;

    @Override
    public void handle(PayIntoAccountCommand command) {
        Account account = accountFactory.load(command.getAccountId());
        account.payInto(command.getAmount());
    }
}
