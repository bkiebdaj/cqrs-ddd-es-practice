package org.bkiebdaj.cqrsexample.domain.account.command.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.api.CommandHandler;
import org.bkiebdaj.cqrsexample.core.common.AggregateId;
import org.bkiebdaj.cqrsexample.domain.account.aggregate.Account;
import org.bkiebdaj.cqrsexample.domain.account.command.CreateAccountCommand;
import org.bkiebdaj.cqrsexample.domain.account.repository.AccountRepository;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateAccountCommandHandler implements CommandHandler<CreateAccountCommand> {

    private final AccountRepository accountRepository;

    @Override
    public void handle(CreateAccountCommand command) {
        log.info("Handle command: {}", command);
        Account account = accountRepository.load(AggregateId.create());
        account.createAccount();
        accountRepository.persist(account);
    }
}
