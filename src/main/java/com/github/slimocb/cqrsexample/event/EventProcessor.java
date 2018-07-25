package com.github.slimocb.cqrsexample.event;

import com.github.slimocb.cqrsexample.api.EventPayload;
import com.github.slimocb.cqrsexample.message.EventMessageBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class EventProcessor implements Runnable {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final EventMessageBus eventMessageBus;
    private final SimpleEventDispatcher eventDispatcher;

    public EventProcessor(EventMessageBus eventMessageBus, SimpleEventDispatcher eventDispatcher) {
        this.eventMessageBus = eventMessageBus;
        this.eventDispatcher = eventDispatcher;
        executorService.execute(this);
    }

    @Override
    public void run() {
        while (true) {
            try {
                EventPayload event = eventMessageBus.poll();
                eventDispatcher.dispatchEvent(event);
            } catch (Exception e) {
                log.error("Error while processing command", e);
            }
        }
    }
}
