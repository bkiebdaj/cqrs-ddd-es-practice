package org.bkiebdaj.cqrsexample.domain.account.command;

import lombok.Value;
import org.bkiebdaj.cqrsexample.core.api.Command;

@Value
public class CreateAccountCommand implements Command {
    String username;
    String email;
}
