package org.bkiebdaj.cqrsexample.core.event.store;

import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.common.AggregadeId;
import org.bkiebdaj.cqrsexample.core.event.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class EventStore {
    private final Collection<Event> data = Collections.synchronizedCollection(new ArrayList<>());

    public void save(Event event) {
        log.info("Store event: {}", event);
        data.add(event);
    }

    public List<Event> findAllBy(AggregadeId aggregadeId) {
        return data.stream()
                .filter(x -> x.getAggregadeId().equals(aggregadeId))
                .collect(Collectors.toList());
    }

    public List<Event> findAll() {
        return new ArrayList<>(data);
    }
}
