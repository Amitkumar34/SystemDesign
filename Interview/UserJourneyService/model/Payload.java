package Interview.UserJourneyService.model;

import java.util.HashMap;
import java.util.Map;

public class Payload {
    private final String userId;
    private final String eventType;
    private final Map<String, String> attributes;

    public Payload(String userId, String eventType, Map<String, String> attributes) {
        this.userId = userId;
        this.eventType = eventType;
        this.attributes = new HashMap<>(attributes);
    }

    public String getUserId() {
        return userId;
    }

    public String getEventType() {
        return eventType;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }
}
