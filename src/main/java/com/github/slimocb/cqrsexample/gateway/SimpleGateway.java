package com.github.slimocb.cqrsexample.gateway;

import com.github.slimocb.cqrsexample.api.Command;
import com.github.slimocb.cqrsexample.api.EventPayload;
import com.github.slimocb.cqrsexample.api.Gateway;
import com.github.slimocb.cqrsexample.message.CommandMessageBus;
import com.github.slimocb.cqrsexample.message.EventMessageBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SimpleGateway implements Gateway {

    private final CommandMessageBus commandMessageBus;
    private final EventMessageBus eventMessageBus;

    public SimpleGateway(CommandMessageBus commandMessageBus, EventMessageBus eventDispatcher) {
        log.info("Gateway is up!");
        this.commandMessageBus = commandMessageBus;
        this.eventMessageBus = eventDispatcher;
    }

    @Override
    public void publishCommand(Command command) {
        commandMessageBus.publish(command);
    }

    @Override
    public void publishEvent(EventPayload event) {
        eventMessageBus.publish(event);
    }
}
