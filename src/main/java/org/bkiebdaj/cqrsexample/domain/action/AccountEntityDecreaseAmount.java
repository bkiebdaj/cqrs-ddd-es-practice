package org.bkiebdaj.cqrsexample.domain.action;

import lombok.RequiredArgsConstructor;
import org.bkiebdaj.cqrsexample.api.EventHandler;
import org.bkiebdaj.cqrsexample.domain.event.AccountMoneyAmountDecreased;
import org.bkiebdaj.cqrsexample.domain.service.accountpresentation.AccountPresentationService;
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