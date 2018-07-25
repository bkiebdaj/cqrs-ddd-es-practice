package com.github.slimocb.cqrsexample.domain.account;

import com.github.slimocb.cqrsexample.api.Gateway;
import com.github.slimocb.cqrsexample.common.AggregadeId;
import com.github.slimocb.cqrsexample.common.Event;
import com.github.slimocb.cqrsexample.event.store.EventStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
