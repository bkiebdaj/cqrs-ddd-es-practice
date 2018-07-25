package org.bkiebdaj.cqrsexample.core.message;

import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.api.Command;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommandMessageBus extends SimpleMessageBus<Command> {
}
