package com.github.slimocb.cqrsexample.domain.service.accountpresentation;

import com.github.slimocb.cqrsexample.domain.event.AccountCreatedEvent;
import com.github.slimocb.cqrsexample.domain.event.AccountMoneyAmountDecreasedEvent;
import com.github.slimocb.cqrsexample.domain.event.AccountMoneyAmountIncreasedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class AccountPresentationService {
    private final AccountRepository accountRepository = new AccountRepository();

    public void handle(AccountCreatedEvent event) {
        AccountEntity accountEntity = new AccountEntity(event.getAggregadeId().getId().toString(), event.getAccountNumber(), BigDecimal.ZERO);
        accountRepository.save(accountEntity);
    }

    public void handle(AccountMoneyAmountIncreasedEvent event) {
        AccountEntity accountEntity = accountRepository.findById(event.getAggregadeId().getId().toString());
        accountEntity.increaseAmount(event.getAmount());
    }

    public void handle(AccountMoneyAmountDecreasedEvent event) {
        AccountEntity accountEntity = accountRepository.findById(event.getAggregadeId().getId().toString());
        accountEntity.decreaseAmount(event.getAmount());
    }

    public List<AccountEntity> getAllAccounts() {
        return accountRepository.findAll();
    }
}
