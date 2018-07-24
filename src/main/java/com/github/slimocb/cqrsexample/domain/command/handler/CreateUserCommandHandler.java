package com.github.slimocb.cqrsexample.domain.command.handler;

import com.github.slimocb.cqrsexample.api.CommandHandler;
import com.github.slimocb.cqrsexample.api.Gateway;
import com.github.slimocb.cqrsexample.common.AggregadeId;
import com.github.slimocb.cqrsexample.domain.account.Account;
import com.github.slimocb.cqrsexample.domain.account.AccountFactory;
import com.github.slimocb.cqrsexample.domain.command.CreateUserCommand;
import com.github.slimocb.cqrsexample.domain.user.User;
import com.github.slimocb.cqrsexample.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
