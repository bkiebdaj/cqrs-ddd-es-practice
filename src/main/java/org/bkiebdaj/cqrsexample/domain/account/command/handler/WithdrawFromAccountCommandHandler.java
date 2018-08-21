package org.bkiebdaj.cqrsexample.domain.account.command.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.api.CommandHandler;
import org.bkiebdaj.cqrsexample.domain.account.aggregate.Account;
import org.bkiebdaj.cqrsexample.domain.account.command.WithdrawFromAccountCommand;
import org.bkiebdaj.cqrsexample.domain.account.repository.AccountRepository;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WithdrawFromAccountCommandHandler implements CommandHandler<WithdrawFromAccountCommand> {

    private final AccountRepository accountRepository;

    @Override
    public void handle(WithdrawFromAccountCommand command) {
        log.info("Handle command: {}", command);
        Account account = accountRepository.load(command.getAccountId());
        account.withdraw(command.getAmount());
        accountRepository.persist(account);
    }
}
