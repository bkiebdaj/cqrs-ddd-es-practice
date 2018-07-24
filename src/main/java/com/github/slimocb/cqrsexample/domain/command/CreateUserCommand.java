package com.github.slimocb.cqrsexample.domain.command;

import com.github.slimocb.cqrsexample.api.Command;
import lombok.Value;

@Value
public class CreateUserCommand implements Command {
    String username;
    String email;
}
