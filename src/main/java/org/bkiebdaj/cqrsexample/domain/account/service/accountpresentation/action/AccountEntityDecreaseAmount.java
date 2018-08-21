package org.bkiebdaj.cqrsexample.domain.account.service.accountpresentation.action;

import lombok.RequiredArgsConstructor;
import org.bkiebdaj.cqrsexample.core.api.EventHandler;
import org.bkiebdaj.cqrsexample.domain.account.event.AccountMoneyAmountDecreased;
import org.bkiebdaj.cqrsexample.domain.account.service.accountpresentation.AccountPresentationService;
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