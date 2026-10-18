package Interview.UserJourneyService.model;

import java.util.HashMap;
import java.util.Map;

//Stage: Contains stageNumber (int, 1-indexed), conditions (Map<String,
//        String>)


public class Stage {
    private final int stageNumber;
    private final Map<String, String> conditions;

    public Stage(int stageNumber, Map<String, String> conditions) {
        this.stageNumber = stageNumber;
        this.conditions = new HashMap<>(conditions);
    }

    public int getStageNumber() {
        return stageNumber;
    }

    public Map<String, String> getConditions() {
        return conditions;
    }
}
