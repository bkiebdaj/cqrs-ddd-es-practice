package com.github.slimocb.cqrsexample.domain.command.handler;

import com.github.slimocb.cqrsexample.api.CommandHandler;
import com.github.slimocb.cqrsexample.domain.command.SendMailToUserCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendMailToUserCommandHandler implements CommandHandler<SendMailToUserCommand> {
    @Override
    public void handle(SendMailToUserCommand command) {

    }
}
