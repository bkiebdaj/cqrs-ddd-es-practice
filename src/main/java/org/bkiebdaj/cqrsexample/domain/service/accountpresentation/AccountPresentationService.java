package org.bkiebdaj.cqrsexample.domain.service.accountpresentation;

import org.bkiebdaj.cqrsexample.domain.event.AccountCreatedEvent;
import org.bkiebdaj.cqrsexample.domain.event.AccountMoneyAmountDecreasedEvent;
import org.bkiebdaj.cqrsexample.domain.event.AccountMoneyAmountIncreasedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class AccountPresentationService {
    private final AccountRepository accountRepository = new AccountRepository();

    public void handle(AccountCreatedEvent event) {
        AccountEntity accountEntity = new AccountEntity(event.getAggregadeId().getId().toString(), event.getPayload().getAccountNumber(), BigDecimal.ZERO);
        accountRepository.save(accountEntity);
    }

    public void handle(AccountMoneyAmountIncreasedEvent event) {
        AccountEntity accountEntity = accountRepository.findById(event.getAggregadeId().getId().toString());
        accountEntity.increaseAmount(event.getPayload().getAmount());
    }

    public void handle(AccountMoneyAmountDecreasedEvent event) {
        AccountEntity accountEntity = accountRepository.findById(event.getAggregadeId().getId().toString());
        accountEntity.decreaseAmount(event.getPayload().getAmount());
    }

    public List<AccountEntity> getAllAccounts() {
        return accountRepository.findAll();
    }
}
