package org.bkiebdaj.cqrsexample.core.event;

import com.google.gson.Gson;
import org.bkiebdaj.cqrsexample.core.api.Event;

public class EventSerializer {
    private static final Gson gson = new Gson();

    public static String serialize(Event event) {
        return gson.toJson(event);
    }
}
