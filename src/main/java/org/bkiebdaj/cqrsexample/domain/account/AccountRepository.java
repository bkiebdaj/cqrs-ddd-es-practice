package org.bkiebdaj.cqrsexample.domain.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.api.Event;
import org.bkiebdaj.cqrsexample.core.common.AggregateId;
import org.bkiebdaj.cqrsexample.core.event.store.EventStore;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountRepository {

    private final EventStore eventStore;

    public Account create() {
        return new Account(AggregateId.create());
    }

    public Account load(AggregateId aggregateId) {
        List<Event> events = eventStore.findAllBy(aggregateId);
        if (events.isEmpty()) {
            throw new IllegalStateException("Account not exists: " + aggregateId.getId());
        }
        Account account = new Account(aggregateId);
        account.apply(events);
        return account;
    }

    public void persist(Account account) {
        account.getFreshEvents().forEach(eventStore::save);
        account.markAsUpdated();
    }
}
