package org.bkiebdaj.cqrsexample.domain.account.command;

import lombok.Value;
import org.bkiebdaj.cqrsexample.core.api.Command;

@Value
public class SendMailToUserCommand implements Command {
}
