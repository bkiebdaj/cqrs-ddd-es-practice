package com.github.slimocb.cqrsexample.api;

public interface CommandHandler<T extends Command> {
    void handle(T command);
}
