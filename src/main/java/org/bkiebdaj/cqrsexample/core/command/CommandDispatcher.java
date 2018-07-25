package org.bkiebdaj.cqrsexample.core.command;

import org.bkiebdaj.cqrsexample.core.api.Command;

public interface CommandDispatcher {
    void dispatchCommand(Command command);
}
