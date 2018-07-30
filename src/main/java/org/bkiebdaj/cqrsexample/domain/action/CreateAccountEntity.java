package org.bkiebdaj.cqrsexample.domain.action;

import lombok.RequiredArgsConstructor;
import org.bkiebdaj.cqrsexample.core.api.EventHandler;
import org.bkiebdaj.cqrsexample.domain.event.AccountCreatedEvent;
import org.bkiebdaj.cqrsexample.domain.service.accountpresentation.AccountPresentationService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateAccountEntity implements EventHandler<AccountCreatedEvent> {

    private final AccountPresentationService accountPresentationService;

    @Override
    public void handle(AccountCreatedEvent event) {
        accountPresentationService.handle(event.getPayload());
    }
}
