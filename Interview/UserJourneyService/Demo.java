package Interview.UserJourneyService;

import Interview.UserJourneyService.enums.JourneyType;
import Interview.UserJourneyService.factory.JourneyFactory;
import Interview.UserJourneyService.model.Journey;
import Interview.UserJourneyService.model.Payload;
import Interview.UserJourneyService.model.Stage;
import Interview.UserJourneyService.repos.JourneyRepo;
import Interview.UserJourneyService.repos.UserJourneyRepo;
import Interview.UserJourneyService.services.JourneyService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
//        runTestCase();
        runByTakingInputs();
    }
    public static void runByTakingInputs() {
        Scanner scanner = new Scanner(System.in);
        int queries = Integer.parseInt(scanner.nextLine().trim());

        JourneyService service = new JourneyService(new JourneyRepo(), new UserJourneyRepo());

        for (int i = 0; i < queries; i++) {
            String line = scanner.nextLine().trim();
            String[] parts = line.split("\\s+");
            //            String[] parts = line.split(" ");
            String output = switch (parts[0]) {
                case "CREATE" -> handleCreate(service, parts);
                case "UPDATE_STATE" -> handleUpdateState(service, parts);
                case "GET_JOURNEY" -> handleGetJourney(service, parts);
                case "EVALUATE" -> handleEvaluate(service, parts);
                case "GET_CURRENT_STAGE" -> handleGetCurrentStage(service, parts);
                case "IS_ONBOARDED" -> handleIsOnboarded(service, parts);
                default -> throw new IllegalArgumentException("Unknown QueryType: " + parts[0]);
            };
            System.out.println(output);
        }
    }

    private static String handleCreate(JourneyService service, String[] parts) {
        String journeyId = parts[1];
        JourneyType journeyType = JourneyType.valueOf(parts[2]);
        String startDate = "null".equals(parts[3]) ? null : parts[3];
        String endDate = "null".equals(parts[4]) ? null : parts[3];
        int numStages = Integer.parseInt(parts[5]);

        List<Stage> stages = new ArrayList<>();
        for (int i = 0; i < numStages; i++) {
            int stageNumber = i + 1;
            Map<String, String> conditions = readConditions(parts[6 + i]);
            stages.add(new Stage(stageNumber, conditions));
        }

        Journey journey = JourneyFactory.create(journeyId, stages, false, journeyType, startDate, endDate);
        return service.createJourney(journey);
    }

    private static String handleUpdateState(JourneyService service, String[] parts) {
        String journeyId = parts[1];
        boolean active = Boolean.parseBoolean(parts[2]);
        return String.valueOf(service.updateState(journeyId, active));
    }

    private static String handleGetJourney(JourneyService service, String[] parts) {
        Journey journey = service.getJourney(parts[1]);
        return journey == null ? "null" : journey.getJourneyId();
    }

    private static String handleEvaluate(JourneyService service, String[] parts) {
        String userId = parts[1];
        String eventType = parts[2];
        Map<String, String> attributes = new HashMap<>();
        for (int i = 3; i < parts.length; i++) {
            String[] kv = parts[i].split(":");
            attributes.put(kv[0], kv[1]);
        }
        Payload payload = new Payload(userId, eventType, attributes);
        List<String> result = service.evaluate(userId, payload);
        if (result.isEmpty()) {
            return "NONE";
        }
        return String.join(",", result);
    }

    private static String handleGetCurrentStage(JourneyService service, String[] parts) {
        return String.valueOf(service.getCurrentStage(parts[1], parts[2]));
    }

    private static String handleIsOnboarded(JourneyService service, String[] parts) {
        return String.valueOf(service.isOnboarded(parts[1], parts[2]));
    }

    private static Map<String, String> readConditions(String str) {
        Map<String, String> conditions = new HashMap<>();
        if (str.isEmpty()) {
            return conditions;
        }
        String[] pairs = str.split(",");
        for (String pair : pairs) {
            String[] kv = pair.split("=");
            conditions.put(kv[0], kv[1]);
        }
        return conditions;
    }

    public static void runTestCase() {
        String[] lines = {
                "CREATE J1 PERPETUAL null null 3 eventType=LOGIN eventType=RECHARGE_PAGE eventType=RECHARGE_DONE",
                "CREATE J2 TIME_BOUND 2025-01-01 2025-12-31 2 eventType=UPI_LITE_OPEN eventType=UPI_LITE_TOPUP",
                "UPDATE_STATE J1 true",
                "UPDATE_STATE J2 true",
                "EVALUATE U1 LOGIN",
                "EVALUATE U1 RECHARGE_PAGE",
                "EVALUATE U2 LOGIN",
                "EVALUATE U2 RECHARGE_PAGE",
                "EVALUATE U2 RECHARGE_DONE",
                "GET_CURRENT_STAGE U1 J1"
        };

        JourneyService service = new JourneyService(new JourneyRepo(), new UserJourneyRepo());

        for (String line : lines) {
            String[] parts = line.split("\\s+");
            String output = switch (parts[0]) {
                case "CREATE" -> handleCreate(service, parts);
                case "UPDATE_STATE" -> handleUpdateState(service, parts);
                case "GET_JOURNEY" -> handleGetJourney(service, parts);
                case "EVALUATE" -> handleEvaluate(service, parts);
                case "GET_CURRENT_STAGE" -> handleGetCurrentStage(service, parts);
                case "IS_ONBOARDED" -> handleIsOnboarded(service, parts);
                default -> throw new IllegalArgumentException("Unknown QueryType: " + parts[0]);
            };
            System.out.println(output);
        }
    }
}
