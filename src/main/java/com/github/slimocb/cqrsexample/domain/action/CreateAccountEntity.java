package com.github.slimocb.cqrsexample.domain.action;

import com.github.slimocb.cqrsexample.api.EventHandler;
import com.github.slimocb.cqrsexample.domain.event.AccountCreated;
import com.github.slimocb.cqrsexample.domain.service.accountpresentation.AccountPresentationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateAccountEntity implements EventHandler<AccountCreated> {

    private final AccountPresentationService accountPresentationService;

    @Override
    public void handle(AccountCreated event) {
        accountPresentationService.handle(event);
    }
}
