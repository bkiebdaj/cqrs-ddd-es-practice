package org.bkiebdaj.cqrsexample.api;

public interface CommandHandler<T extends Command> {
    void handle(T command);
}
