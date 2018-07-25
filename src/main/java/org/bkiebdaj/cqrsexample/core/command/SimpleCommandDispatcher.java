package org.bkiebdaj.cqrsexample.core.command;

import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.api.Command;
import org.bkiebdaj.cqrsexample.core.api.CommandHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class SimpleCommandDispatcher implements CommandDispatcher {

    private final CommandHandlerRegister commandHandlerRegister = new CommandHandlerRegister();
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    public SimpleCommandDispatcher(List<CommandHandler> commandHandlers) {
        commandHandlers.forEach(commandHandlerRegister::registerHandler);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void dispatchCommand(Command command) {
        log.info("Dispatch command: {}", command.getClass().getSimpleName());
        CommandHandler commandHandler = commandHandlerRegister.get(command);
        log.info("Found command handler: {}", commandHandler.getClass().getSimpleName());
        executor.execute(() -> commandHandler.handle(command));
    }
}
