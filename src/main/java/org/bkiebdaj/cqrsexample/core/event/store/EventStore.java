package org.bkiebdaj.cqrsexample.core.event.store;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.api.Event;
import org.bkiebdaj.cqrsexample.core.api.Gateway;
import org.bkiebdaj.cqrsexample.core.common.AggregateId;
import org.bkiebdaj.cqrsexample.core.event.EventDeserializer;
import org.bkiebdaj.cqrsexample.core.event.EventEntity;
import org.bkiebdaj.cqrsexample.core.event.EventSerializer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventStore {

    private final Collection<EventEntity> data = Collections.synchronizedCollection(new ArrayList<>());
    private final Gateway gateway;

    public void save(Event event) {
        log.info("Store event: {}", event);
        EventEntity eventEntity = EventEntity.builder()
                .aggregateId(event.getAggregateId())
                .aggregateType("Account")
                .eventType(event.getClass().getSimpleName())
                .payload(EventSerializer.serialize(event))
                .build();
        data.add(eventEntity);
        gateway.publishEvent(eventEntity);
    }

    public List<Event> findAllBy(AggregateId aggregateId) {
        return data.stream()
                .filter(x -> x.getAggregateId().equals(aggregateId))
                .map(EventDeserializer::deserialize)
                .collect(Collectors.toList());
    }

    public List<EventEntity> findAll() {
        return new ArrayList<>(data);
    }
}
