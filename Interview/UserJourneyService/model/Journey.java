package Interview.UserJourneyService.model;

import Interview.UserJourneyService.enums.JourneyType;

import java.util.ArrayList;
import java.util.List;

public abstract class Journey {
    protected final String journeyId;
    protected final List<Stage> stages;
    protected boolean isActive;
    protected final JourneyType journeyType;
    protected final String startDate;
    protected final String endDate;

    protected Journey(
            String journeyId,
            List<Stage> stages,
            boolean isActive,
            JourneyType journeyType,
            String startDate,
            String endDate) {
        this.journeyId = journeyId;
        this.stages = new ArrayList<>(stages);
        this.isActive = isActive;
        this.journeyType = journeyType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getJourneyId() {
        return journeyId;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public boolean isActive() {
        return isActive;
    }

    public JourneyType getJourneyType() {
        return journeyType;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }
}
