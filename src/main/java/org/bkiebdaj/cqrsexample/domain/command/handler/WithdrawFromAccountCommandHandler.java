package org.bkiebdaj.cqrsexample.domain.command.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.api.CommandHandler;
import org.bkiebdaj.cqrsexample.api.Gateway;
import org.bkiebdaj.cqrsexample.domain.account.Account;
import org.bkiebdaj.cqrsexample.domain.account.AccountFactory;
import org.bkiebdaj.cqrsexample.domain.command.WithdrawFromAccountCommand;
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
