package org.bkiebdaj.cqrsexample.core.api;

import org.bkiebdaj.cqrsexample.core.common.AggregadeId;

public interface EventPayload {
    AggregadeId getAggregadeId();
}
