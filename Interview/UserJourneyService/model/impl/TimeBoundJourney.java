package Interview.UserJourneyService.model.impl;

import Interview.UserJourneyService.enums.JourneyType;
import Interview.UserJourneyService.model.Journey;
import Interview.UserJourneyService.model.Stage;

import java.util.List;

public class TimeBoundJourney extends Journey {

    public TimeBoundJourney(
            String journeyId,
            List<Stage> stages,
            boolean isActive,
            String startDate,
            String endDate) {
        super(journeyId, stages, isActive, JourneyType.TIME_BOUND, startDate, endDate);
    }
}
