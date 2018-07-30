package org.bkiebdaj.cqrsexample.domain.service.accountpresentation.action;

import lombok.RequiredArgsConstructor;
import org.bkiebdaj.cqrsexample.core.api.EventHandler;
import org.bkiebdaj.cqrsexample.domain.event.AccountMoneyAmountDecreasedEvent;
import org.bkiebdaj.cqrsexample.domain.service.accountpresentation.AccountPresentationService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountEntityDecreaseAmount implements EventHandler<AccountMoneyAmountDecreasedEvent> {

    private final AccountPresentationService accountPresentationService;

    @Override
    public void handle(AccountMoneyAmountDecreasedEvent event) {
        accountPresentationService.handle(event.getPayload());
    }
}