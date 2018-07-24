package com.github.slimocb.cqrsexample.message;

import com.github.slimocb.cqrsexample.api.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommandMessageBus extends SimpleMessageBus<Command> {
}
