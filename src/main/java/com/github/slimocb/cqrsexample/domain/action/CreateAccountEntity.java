package com.github.slimocb.cqrsexample.domain.action;

import com.github.slimocb.cqrsexample.api.EventHandler;
import com.github.slimocb.cqrsexample.domain.event.AccountCreatedEvent;
import com.github.slimocb.cqrsexample.domain.service.accountpresentation.AccountPresentationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateAccountEntity implements EventHandler<AccountCreatedEvent> {

    private final AccountPresentationService accountPresentationService;

    @Override
    public void handle(AccountCreatedEvent event) {
        accountPresentationService.handle(event);
    }
}
