package org.bkiebdaj.cqrsexample.core.command;

import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.api.Command;
import org.bkiebdaj.cqrsexample.core.api.CommandHandler;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
class CommandHandlerRegister {
    private final Map<Class<? extends Command>, CommandHandler> handlers = new HashMap<>();

    CommandHandler get(Command command) {
        return handlers.get(command.getClass());
    }

    void registerHandler(CommandHandler commandHandler) {
        Class<? extends Command> handledCommand = findHandledCommand(commandHandler);

        handlers.put(handledCommand, commandHandler);

        log.info("Successfully registered command handler: {}", commandHandler.getClass().getSimpleName());
    }

    private Class<? extends Command> findHandledCommand(CommandHandler commandHandler) {
        Method[] methods = commandHandler.getClass().getMethods();

        return Stream.of(methods)
                .map(Method::getParameterTypes)
                .flatMap(Stream::of)
                .filter(x -> !x.isInterface())
                .map(x -> (Class<? extends Command>) x)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Can't determine handler type: " + commandHandler.getClass().getSimpleName()));
    }
}
