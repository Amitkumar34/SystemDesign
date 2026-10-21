package Interview.UserJourneyService.model.impl;

import Interview.UserJourneyService.enums.JourneyType;
import Interview.UserJourneyService.model.Journey;
import Interview.UserJourneyService.model.Stage;
import lombok.Getter;

import java.util.List;

@Getter
public class TimeBoundJourney extends Journey {
    public static final String DATE_OF_EVALUATION = "2025-01-15";

    private final String startDate;
    private final String endDate;

    public TimeBoundJourney(
            String journeyId,
            List<Stage> stages,
            boolean isActive,
            String startDate,
            String endDate) {
        super(journeyId, stages, isActive, JourneyType.TIME_BOUND);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean isEligibleForEvaluation() {
        return isActive && startDate != null
                && endDate != null
                && startDate.compareTo(DATE_OF_EVALUATION) <= 0
                && DATE_OF_EVALUATION.compareTo(endDate) <= 0;
    }
}
