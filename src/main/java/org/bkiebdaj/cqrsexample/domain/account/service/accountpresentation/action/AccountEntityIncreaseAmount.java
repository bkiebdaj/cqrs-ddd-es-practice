package org.bkiebdaj.cqrsexample.domain.account.service.accountpresentation.action;

import lombok.RequiredArgsConstructor;
import org.bkiebdaj.cqrsexample.core.api.EventHandler;
import org.bkiebdaj.cqrsexample.domain.account.event.AccountMoneyAmountIncreased;
import org.bkiebdaj.cqrsexample.domain.account.service.accountpresentation.AccountPresentationService;
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