package org.bkiebdaj.cqrsexample.core.aggreagate.exception;

public class AggregateException extends RuntimeException {
    public AggregateException(String message, Throwable cause) {
        super(message, cause);
    }
}
