package com.github.slimocb.cqrsexample.domain.service.accountpresentation;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AccountRepository {
    List<AccountEntity> accounts = new CopyOnWriteArrayList<>();

    public void save(AccountEntity accountEntity) {
        accounts.add(accountEntity);
    }

    public AccountEntity findById(String id) {
        return accounts.stream()
                .filter(ac -> ac.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("wrong"));
    }

    public List<AccountEntity> findAll() {
        return accounts;
    }
}
