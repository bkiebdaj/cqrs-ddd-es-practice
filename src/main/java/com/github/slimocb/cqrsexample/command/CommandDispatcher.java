package com.github.slimocb.cqrsexample.command;

import com.github.slimocb.cqrsexample.api.Command;

public interface CommandDispatcher {
    void dispatchCommand(Command command);
}
