package model;

import java.util.UUID;

public class EventID {
    private final UUID eventId;

    public EventID(UUID eventId) {
        this.eventId = eventId;
    }

    public UUID getEventId() {
        return eventId;
    }
}

