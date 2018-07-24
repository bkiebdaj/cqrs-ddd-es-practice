package com.github.slimocb.cqrsexample.event.store;

import com.github.slimocb.cqrsexample.api.Event;
import com.github.slimocb.cqrsexample.common.AggregadeId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

@Slf4j
@Component
public class EventStore {
    Collection<Event> data = Collections.synchronizedCollection(new ArrayList<Event>());

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
