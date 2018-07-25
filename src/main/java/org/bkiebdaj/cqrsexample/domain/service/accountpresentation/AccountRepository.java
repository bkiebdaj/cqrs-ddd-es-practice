package org.bkiebdaj.cqrsexample.domain.service.accountpresentation;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class AccountRepository {
    private final List<AccountEntity> accounts = new CopyOnWriteArrayList<>();

    void save(AccountEntity accountEntity) {
        accounts.add(accountEntity);
    }

    AccountEntity findById(String id) {
        return accounts.stream()
                .filter(ac -> ac.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("wrong"));
    }

    List<AccountEntity> findAll() {
        return accounts;
    }
}
