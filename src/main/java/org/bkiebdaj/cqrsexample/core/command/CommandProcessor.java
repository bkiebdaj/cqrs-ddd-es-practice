package org.bkiebdaj.cqrsexample.core.command;

import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.api.Command;
import org.bkiebdaj.cqrsexample.core.message.CommandMessageBus;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class CommandProcessor implements Runnable {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final CommandMessageBus commandMessageBus;
    private final SimpleCommandDispatcher commandDispatcher;

    public CommandProcessor(CommandMessageBus commandMessageBus, SimpleCommandDispatcher commandDispatcher) {
        this.commandMessageBus = commandMessageBus;
        this.commandDispatcher = commandDispatcher;
        this.executorService.execute(this);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Command command = commandMessageBus.poll();
                commandDispatcher.dispatchCommand(command);
            } catch (Exception e) {
                log.error("Error while processing command", e);
            }
        }
    }
}
