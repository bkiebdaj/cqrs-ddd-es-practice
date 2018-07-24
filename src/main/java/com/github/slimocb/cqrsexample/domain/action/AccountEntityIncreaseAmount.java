package com.github.slimocb.cqrsexample.domain.action;

import com.github.slimocb.cqrsexample.api.EventHandler;
import com.github.slimocb.cqrsexample.domain.event.AccountCreatedEvent;
import com.github.slimocb.cqrsexample.domain.event.AccountMoneyAmountIncreasedEvent;
import com.github.slimocb.cqrsexample.domain.service.accountpresentation.AccountPresentationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountEntityIncreaseAmount implements EventHandler<AccountMoneyAmountIncreasedEvent> {

    private final AccountPresentationService accountPresentationService;

    @Override
    public void handle(AccountMoneyAmountIncreasedEvent event) {
        accountPresentationService.handle(event);
    }
}