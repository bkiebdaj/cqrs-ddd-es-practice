package org.bkiebdaj.cqrsexample.domain.command.handler;

import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.api.CommandHandler;
import org.bkiebdaj.cqrsexample.domain.command.SendMailToUserCommand;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendMailToUserCommandHandler implements CommandHandler<SendMailToUserCommand> {
    @Override
    public void handle(SendMailToUserCommand command) {

    }
}
