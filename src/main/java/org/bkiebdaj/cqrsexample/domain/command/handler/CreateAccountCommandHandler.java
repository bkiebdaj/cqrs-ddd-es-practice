package org.bkiebdaj.cqrsexample.domain.command.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.api.CommandHandler;
import org.bkiebdaj.cqrsexample.core.event.Event;
import org.bkiebdaj.cqrsexample.domain.account.Account;
import org.bkiebdaj.cqrsexample.domain.account.AccountRepository;
import org.bkiebdaj.cqrsexample.domain.command.CreateAccountCommand;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateAccountCommandHandler implements CommandHandler<CreateAccountCommand> {

    private final AccountRepository accountRepository;

    @Override
    public void handle(CreateAccountCommand command) {
        log.info("Handle command: {}", command);
        Account account = accountRepository.create();
        List<Event> events = account.handle(command);
        accountRepository.publishEvents(events);
    }
}
