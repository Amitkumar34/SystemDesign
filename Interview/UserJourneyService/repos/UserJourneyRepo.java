package Interview.UserJourneyService.repos;

import java.util.HashMap;
import java.util.Map;

public class UserJourneyRepo {
    private final Map<String, Map<String, Integer>> userStageByJourney = new HashMap<>();

    public Map<String,Integer> getUserJourney(String userId){
        return userStageByJourney.getOrDefault(userId, new HashMap<>());
    }

    public Integer getUserStage(String userId, String journeyId) {
        Map<String, Integer> userJourneys = userStageByJourney.get(userId);
        return userJourneys == null ? null : userJourneys.get(journeyId);
    }

    public void setUserStage(String userId, String journeyId, int stage) {
        Map<String, Integer> userJourneys = userStageByJourney.getOrDefault(userId, new HashMap<>());
        userJourneys.put(journeyId,stage);
        userStageByJourney.put(userId,userJourneys);
    }
}
