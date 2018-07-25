package org.bkiebdaj.cqrsexample.domain.command.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.api.CommandHandler;
import org.bkiebdaj.cqrsexample.api.Gateway;
import org.bkiebdaj.cqrsexample.common.AggregadeId;
import org.bkiebdaj.cqrsexample.domain.account.Account;
import org.bkiebdaj.cqrsexample.domain.account.AccountFactory;
import org.bkiebdaj.cqrsexample.domain.command.CreateUserCommand;
import org.bkiebdaj.cqrsexample.domain.repository.UserRepository;
import org.bkiebdaj.cqrsexample.domain.user.User;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand> {

    private final Gateway gateway;
    private final UserRepository userRepository;
    private final AccountFactory accountFactory;

    @Override
    public void handle(CreateUserCommand command) {
        log.info("Handle command: {}", command);

        if (userRepository.exists(command.getUsername(), command.getEmail())) {
            log.error("User already exists: {}", command);
            return;
        }

        Account account = accountFactory.create();

        User user = new User(AggregadeId.create(), account.getAggregadeId(), command.getUsername(), command.getEmail());
        userRepository.save(user);
    }
}
