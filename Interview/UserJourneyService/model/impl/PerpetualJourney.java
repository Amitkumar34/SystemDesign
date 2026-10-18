package Interview.UserJourneyService.model.impl;

import Interview.UserJourneyService.enums.JourneyType;
import Interview.UserJourneyService.model.Journey;
import Interview.UserJourneyService.model.Stage;

import java.util.List;

public class PerpetualJourney extends Journey {

    public PerpetualJourney(String journeyId, List<Stage> stages, boolean isActive) {
        super(journeyId, stages, isActive, JourneyType.PERPETUAL, null, null);
    }
}
