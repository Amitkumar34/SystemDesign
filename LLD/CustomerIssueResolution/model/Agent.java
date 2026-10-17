package LLD.CustomerIssueResolution.model;

import LLD.CustomerIssueResolution.enums.IssueType;
import lombok.Data;

import java.util.*;

@Data
public class Agent {
    private final String id;
    private final String name;
    private final String email;
    private final Set<IssueType> expertises;

    private String assignedIssueId;
    private final Queue<String> waitList =new LinkedList<>();
    private final List<String> history = new ArrayList<>();

    public boolean isAvailable() {
        return assignedIssueId == null;
    }
}
