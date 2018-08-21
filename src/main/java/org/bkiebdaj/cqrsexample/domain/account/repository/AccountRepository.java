package org.bkiebdaj.cqrsexample.domain.account.repository;

import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.aggreagate.AggregateRepository;
import org.bkiebdaj.cqrsexample.core.event.store.EventStore;
import org.bkiebdaj.cqrsexample.domain.account.aggregate.Account;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountRepository extends AggregateRepository<Account> {

    public AccountRepository(EventStore eventStore) {
        super(eventStore, Account.class);
    }
}
