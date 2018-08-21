package org.bkiebdaj.cqrsexample.core.aggreagate;

import lombok.RequiredArgsConstructor;
import org.bkiebdaj.cqrsexample.core.aggreagate.exception.AggregateException;
import org.bkiebdaj.cqrsexample.core.api.Event;
import org.bkiebdaj.cqrsexample.core.common.AggregateId;
import org.bkiebdaj.cqrsexample.core.event.store.EventStore;

import java.util.List;

@RequiredArgsConstructor
abstract public class AggregateRepository<T extends AggregateRoot> {

    private final EventStore eventStore;
    private final Class<T> aggregateRootClazz;

    public T load(AggregateId aggregateId) {
        List<Event> events = eventStore.findAllBy(aggregateId);
        T account = createAggregate(aggregateId);
        account.replay(events);
        return account;
    }

    private T createAggregate(AggregateId aggregateId) {
        T account;
        try {
            account = aggregateRootClazz.newInstance();
            account.setAggregateId(aggregateId);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new AggregateException("Can't create aggregate", e);
        }
        return account;
    }

    public void persist(T aggregate) {
        int version = aggregate.getVersion();
        List<Event> uncommittedEvents = aggregate.getUncommittedEvents();

        int updatedVersion = eventStore.save(uncommittedEvents, version, aggregateRootClazz);

        aggregate.markAsCommitted(updatedVersion);
    }
}
