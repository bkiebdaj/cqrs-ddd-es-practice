package com.github.slimocb.cqrsexample.message;

import com.github.slimocb.cqrsexample.api.Command;
import com.github.slimocb.cqrsexample.api.MessageBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
