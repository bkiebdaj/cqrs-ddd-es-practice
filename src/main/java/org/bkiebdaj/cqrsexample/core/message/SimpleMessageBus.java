package org.bkiebdaj.cqrsexample.core.message;

import org.bkiebdaj.cqrsexample.core.api.MessageBus;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

abstract public class SimpleMessageBus<T> implements MessageBus<T> {

    private final BlockingQueue<T> queue = new LinkedBlockingQueue<>();

    @Override
    public void publish(T message) {
        queue.add(message);
    }

    public T poll() throws InterruptedException {
        return queue.take();
    }
}
