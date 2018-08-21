package org.bkiebdaj.cqrsexample.core.event;

import com.google.gson.Gson;
import org.bkiebdaj.cqrsexample.core.api.Event;
import org.bkiebdaj.cqrsexample.domain.account.event.AccountCreated;
import org.bkiebdaj.cqrsexample.domain.account.event.AccountMoneyAmountDecreased;
import org.bkiebdaj.cqrsexample.domain.account.event.AccountMoneyAmountIncreased;

import java.util.HashMap;
import java.util.Map;

public class EventDeserializer {
    private static final Gson gson = new Gson();
    private static Map<String, Class<? extends Event>> deserializationMap = new HashMap<>();

    static {
        deserializationMap.put("AccountCreated", AccountCreated.class);
        deserializationMap.put("AccountMoneyAmountDecreased", AccountMoneyAmountDecreased.class);
        deserializationMap.put("AccountMoneyAmountIncreased", AccountMoneyAmountIncreased.class);
    }

    public static Event deserialize(EventEntity eventEntity) {
        return gson.fromJson(eventEntity.getPayload(), deserializationMap.get(eventEntity.getEventType()));
    }
}
