package Interview.UserJourneyService.factory;

import Interview.UserJourneyService.enums.JourneyType;
import Interview.UserJourneyService.model.Journey;
import Interview.UserJourneyService.model.Stage;
import Interview.UserJourneyService.model.impl.PerpetualJourney;
import Interview.UserJourneyService.model.impl.TimeBoundJourney;

import java.util.List;

public class JourneyFactory {

    private JourneyFactory() {
    }

    public static Journey create
            (String journeyId, List<Stage> stages, boolean isActive, JourneyType journeyType, String startDate, String endDate) {
        return switch (journeyType) {
            case PERPETUAL -> new PerpetualJourney(journeyId, stages, isActive);
            case TIME_BOUND -> new TimeBoundJourney(journeyId, stages, isActive, startDate, endDate);
        };
    }
}
