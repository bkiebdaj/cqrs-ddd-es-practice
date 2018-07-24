package com.github.slimocb.cqrsexample.domain.command.handler;

import com.github.slimocb.cqrsexample.api.CommandHandler;
import com.github.slimocb.cqrsexample.api.Gateway;
import com.github.slimocb.cqrsexample.domain.account.Account;
import com.github.slimocb.cqrsexample.domain.account.AccountFactory;
import com.github.slimocb.cqrsexample.domain.command.WithdrawFromAccountCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WithdrawFromAccountCommandHandler implements CommandHandler<WithdrawFromAccountCommand> {

    private final Gateway gateway;
    private final AccountFactory accountFactory;

    @Override
    public void handle(WithdrawFromAccountCommand command) {
        Account account = accountFactory.load(command.getAccountId());

        account.withdraw(command.getAmount());
    }
}
