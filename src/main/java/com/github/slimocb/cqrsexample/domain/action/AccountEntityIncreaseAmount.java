package com.github.slimocb.cqrsexample.domain.action;

import com.github.slimocb.cqrsexample.api.EventHandler;
import com.github.slimocb.cqrsexample.domain.event.AccountMoneyAmountIncreased;
import com.github.slimocb.cqrsexample.domain.service.accountpresentation.AccountPresentationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountEntityIncreaseAmount implements EventHandler<AccountMoneyAmountIncreased> {

    private final AccountPresentationService accountPresentationService;

    @Override
    public void handle(AccountMoneyAmountIncreased event) {
        accountPresentationService.handle(event);
    }
}