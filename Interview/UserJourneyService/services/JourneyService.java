package Interview.UserJourneyService.services;

import Interview.UserJourneyService.model.Journey;
import Interview.UserJourneyService.model.Payload;
import Interview.UserJourneyService.model.Stage;
import Interview.UserJourneyService.repos.JourneyRepo;
import Interview.UserJourneyService.repos.UserJourneyRepo;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JourneyService {

    private final JourneyRepo journeyRepo;
    private final UserJourneyRepo userJourneyRepo;

    public JourneyService(JourneyRepo journeyRepo, UserJourneyRepo userJourneyRepo) {
        this.journeyRepo = journeyRepo;
        this.userJourneyRepo = userJourneyRepo;
    }

    public synchronized String createJourney(Journey journey) {
        if (journeyRepo.isExists(journey.getJourneyId())) {
            throw new RuntimeException("Journey already exists");
        }
        journeyRepo.save(journey);
        return journey.getJourneyId();
    }

    public boolean updateState(String journeyId, boolean active) {
        if (!journeyRepo.isExists(journeyId)) return false;
        journeyRepo.getById(journeyId).setActive(active);
        return true;
    }

    public Journey getJourney(String journeyId) {
        return journeyRepo.getById(journeyId);
    }

    public List<String> evaluate(String userId, Payload payload) {
        Map<String, String> requestCombinedMap = new HashMap<>(payload.getAttributes());
        requestCombinedMap.put("eventType", payload.getEventType());

        List<String> changed = new ArrayList<>();

        for (Journey journey : journeyRepo.getAll()) {
            if (!isEligibleForEvaluation(journey)) continue;

            String journeyId = journey.getJourneyId();
            Integer currentStage = userJourneyRepo.getUserStage(userId, journeyId);
            List<Stage> stages = journey.getStages();
            int nextStageIdx = currentStage == null ? 0 : currentStage;
            Stage nextStage = null;
            if (stages != null && nextStageIdx < stages.size()) {
                nextStage = stages.get(nextStageIdx);
            }

            if (nextStage != null && conditionsMatch(nextStage.getConditions(), requestCombinedMap)) {
                userJourneyRepo.setUserStage(userId, journeyId, nextStageIdx + 1);
                changed.add(journeyId);
            }
        }
        return changed;
    }

    public int getCurrentStage(String userId, String journeyId) {
        Integer stage = userJourneyRepo.getUserStage(userId, journeyId);
        return stage == null ? -1 : stage;
    }

    public boolean isOnboarded(String userId, String journeyId) {
        return userJourneyRepo.getUserStage(userId, journeyId) != null;
    }

    private boolean isEligibleForEvaluation(Journey journey) {
        return journey.isEligibleForEvaluation();
    }

    private static boolean conditionsMatch(Map<String, String> conditions, Map<String, String> combined) {
        for (Map.Entry<String, String> entry : conditions.entrySet()) {
            String value = combined.get(entry.getKey());
            if (value == null || !value.equals(entry.getValue())) {
                return false;
            }
        }
        return true;
    }
}
