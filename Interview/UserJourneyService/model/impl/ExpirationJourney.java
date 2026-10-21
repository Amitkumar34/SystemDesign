package Interview.UserJourneyService.model.impl;

import Interview.UserJourneyService.enums.JourneyType;
import Interview.UserJourneyService.model.Journey;
import Interview.UserJourneyService.model.Stage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ExpirationJourney extends Journey {
    public static long DEFAULT_STAGE_EXPIRATION_IN_DAYS = 7;

    @Setter
    @Getter
    private long stageExpirationLimitInDays;
    @Getter
    @Setter
    private long stageEntryTimeInMs;


    public ExpirationJourney(String journeyId, List<Stage> stages, boolean isActive, String startDate, String endDate) {
        super(journeyId, stages, isActive, JourneyType.EXPIRATION_BY_STAGE);
        stageExpirationLimitInDays = DEFAULT_STAGE_EXPIRATION_IN_DAYS;
    }

    public ExpirationJourney(String journeyId, List<Stage> stages, boolean isActive, String startDate, String endDate,
                                long stageExpirationLimitInDays) {
        super(journeyId, stages, isActive, JourneyType.EXPIRATION_BY_STAGE);
        this.stageExpirationLimitInDays = stageExpirationLimitInDays;
    }

    @Override
    public boolean isEligibleForEvaluation() {
        if(!isActive) return false;
        long stageEntryTime = stageEntryTimeInMs;
        long currentTime = System.currentTimeMillis();
        return  !(currentTime - stageEntryTime >= stageExpirationLimitInDays*24*3600*1000);
    }
}
