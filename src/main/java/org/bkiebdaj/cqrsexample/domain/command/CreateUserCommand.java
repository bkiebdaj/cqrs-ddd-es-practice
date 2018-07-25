package org.bkiebdaj.cqrsexample.domain.command;

import lombok.Value;
import org.bkiebdaj.cqrsexample.api.Command;

@Value
public class CreateUserCommand implements Command {
    String username;
    String email;
}
