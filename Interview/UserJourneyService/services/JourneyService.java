package Interview.UserJourneyService.services;

import Interview.UserJourneyService.enums.JourneyType;
import Interview.UserJourneyService.factory.JourneyFactory;
import Interview.UserJourneyService.model.Journey;
import Interview.UserJourneyService.model.Payload;
import Interview.UserJourneyService.model.Stage;
import Interview.UserJourneyService.repos.JourneyRepo;
import Interview.UserJourneyService.repos.UserJourneyRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JourneyService {
    public static final String DATE_OF_EVALUATION = "2025-01-15";

    private final JourneyRepo journeyRepo;
    private final UserJourneyRepo userJourneyRepo;

    public JourneyService(JourneyRepo journeyRepo, UserJourneyRepo userJourneyRepo) {
        this.journeyRepo = journeyRepo;
        this.userJourneyRepo = userJourneyRepo;
    }

    public String createJourney(Journey journeyDTO) {
        if (journeyRepo.isExists(journeyDTO.getJourneyId())) {
            throw new RuntimeException("Journey already exists");
        }
        Journey journey = JourneyFactory.create(
                journeyDTO.getJourneyId(),
                journeyDTO.getStages(),
                false,
                journeyDTO.getJourneyType(),
                journeyDTO.getStartDate(),
                journeyDTO.getEndDate());
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
        if (!journey.isActive()) return false;

        return switch (journey.getJourneyType()) {
            case PERPETUAL -> true;
            case TIME_BOUND -> {
                String start = journey.getStartDate();
                String end = journey.getEndDate();
                yield start != null
                        && end != null
                        && start.compareTo(DATE_OF_EVALUATION) <= 0
                        && DATE_OF_EVALUATION.compareTo(end) <= 0;
            }
        };
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
