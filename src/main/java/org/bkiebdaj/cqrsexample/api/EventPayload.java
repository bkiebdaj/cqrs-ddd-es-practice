package org.bkiebdaj.cqrsexample.api;

import org.bkiebdaj.cqrsexample.common.AggregadeId;

public interface EventPayload {
    AggregadeId getAggregadeId();
}
