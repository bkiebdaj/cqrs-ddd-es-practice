package org.bkiebdaj.cqrsexample.core.gateway;

import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.api.Command;
import org.bkiebdaj.cqrsexample.core.api.EventPayload;
import org.bkiebdaj.cqrsexample.core.api.Gateway;
import org.bkiebdaj.cqrsexample.core.message.CommandMessageBus;
import org.bkiebdaj.cqrsexample.core.message.EventMessageBus;
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
