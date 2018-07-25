package com.github.slimocb.cqrsexample.domain.service.accountpresentation;

import com.github.slimocb.cqrsexample.domain.event.AccountCreated;
import com.github.slimocb.cqrsexample.domain.event.AccountMoneyAmountDecreased;
import com.github.slimocb.cqrsexample.domain.event.AccountMoneyAmountIncreased;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class AccountPresentationService {
    private final AccountRepository accountRepository = new AccountRepository();

    public void handle(AccountCreated event) {
        AccountEntity accountEntity = new AccountEntity(event.getAggregadeId().getId().toString(), event.getAccountNumber(), BigDecimal.ZERO);
        accountRepository.save(accountEntity);
    }

    public void handle(AccountMoneyAmountIncreased event) {
        AccountEntity accountEntity = accountRepository.findById(event.getAggregadeId().getId().toString());
        accountEntity.increaseAmount(event.getAmount());
    }

    public void handle(AccountMoneyAmountDecreased event) {
        AccountEntity accountEntity = accountRepository.findById(event.getAggregadeId().getId().toString());
        accountEntity.decreaseAmount(event.getAmount());
    }

    public List<AccountEntity> getAllAccounts() {
        return accountRepository.findAll();
    }
}
