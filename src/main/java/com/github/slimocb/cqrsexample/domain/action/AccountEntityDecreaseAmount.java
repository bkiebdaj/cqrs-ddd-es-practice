package com.github.slimocb.cqrsexample.domain.action;

import com.github.slimocb.cqrsexample.api.EventHandler;
import com.github.slimocb.cqrsexample.domain.event.AccountMoneyAmountDecreased;
import com.github.slimocb.cqrsexample.domain.service.accountpresentation.AccountPresentationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountEntityDecreaseAmount implements EventHandler<AccountMoneyAmountDecreased> {

    private final AccountPresentationService accountPresentationService;

    @Override
    public void handle(AccountMoneyAmountDecreased event) {
        accountPresentationService.handle(event);
    }
}