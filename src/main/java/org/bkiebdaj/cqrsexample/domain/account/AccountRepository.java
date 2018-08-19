package org.bkiebdaj.cqrsexample.domain.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.api.Gateway;
import org.bkiebdaj.cqrsexample.core.common.AggregadeId;
import org.bkiebdaj.cqrsexample.core.event.Event;
import org.bkiebdaj.cqrsexample.core.event.store.EventStore;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountRepository {

    private final Gateway gateway;
    private final EventStore eventStore;

    public Account create() {
        return new Account(AggregadeId.create());
    }

    public Account load(AggregadeId aggregadeId) {
        List<Event> events = eventStore.findAllBy(aggregadeId);
        if (events.isEmpty()) {
            throw new IllegalStateException("Account not exists: " + aggregadeId.getId());
        }
        Account account = new Account(aggregadeId);
        account.apply(events);
        return account;
    }

    public void persist(Account account) {
        account.getFreshEvents().forEach(gateway::publishEvent);
        account.markAsUpdated();
    }
}
