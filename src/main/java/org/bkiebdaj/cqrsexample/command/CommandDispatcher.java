package org.bkiebdaj.cqrsexample.command;

import org.bkiebdaj.cqrsexample.api.Command;

public interface CommandDispatcher {
    void dispatchCommand(Command command);
}
