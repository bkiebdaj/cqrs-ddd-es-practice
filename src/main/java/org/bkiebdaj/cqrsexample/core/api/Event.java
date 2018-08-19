package org.bkiebdaj.cqrsexample.core.api;

import org.bkiebdaj.cqrsexample.core.common.AggregateId;

public interface Event {
    AggregateId getAggregateId();
}
