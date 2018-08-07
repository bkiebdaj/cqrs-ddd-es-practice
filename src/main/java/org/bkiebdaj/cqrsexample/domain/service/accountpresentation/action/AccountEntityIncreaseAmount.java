package org.bkiebdaj.cqrsexample.domain.service.accountpresentation.action;

import lombok.RequiredArgsConstructor;
import org.bkiebdaj.cqrsexample.core.api.EventHandler;
import org.bkiebdaj.cqrsexample.domain.event.AccountMoneyAmountIncreasedEvent;
import org.bkiebdaj.cqrsexample.domain.service.accountpresentation.AccountPresentationService;
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