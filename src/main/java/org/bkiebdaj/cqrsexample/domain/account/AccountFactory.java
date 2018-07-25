package org.bkiebdaj.cqrsexample.domain.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.api.Gateway;
import org.bkiebdaj.cqrsexample.common.AggregadeId;
import org.bkiebdaj.cqrsexample.common.Event;
import org.bkiebdaj.cqrsexample.event.store.EventStore;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountFactory {
    private final Gateway gateway;
    private final EventStore eventStore;

    public Account create() {
        return new Account(gateway, AggregadeId.create());
    }

    public Account load(AggregadeId aggregadeId) {
        List<Event> events = eventStore.findAllBy(aggregadeId);
        if (events.isEmpty()) {
            throw new IllegalStateException("Account not exists: " + aggregadeId.getId());
        }
        Account account = new Account(gateway);
        account.replay(events);
        return account;
    }
}
