package Interview.UserJourneyService.repos;

import Interview.UserJourneyService.model.Journey;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class JourneyRepo {
    private final Map<String, Journey> journeys = new LinkedHashMap<>();

    public Journey save(Journey journey) {
        journeys.put(journey.getJourneyId(), journey);
        return journey;
    }

    public boolean isExists(String journeyId) {
        return journeys.containsKey(journeyId);
    }

    public Journey getById(String journeyId) {
        return journeys.get(journeyId);
    }

    public Collection<Journey> getAll() {
        return journeys.values();
    }
}
