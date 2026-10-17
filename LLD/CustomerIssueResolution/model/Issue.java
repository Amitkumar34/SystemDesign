package LLD.CustomerIssueResolution.model;

import LLD.CustomerIssueResolution.enums.IssueType;
import LLD.CustomerIssueResolution.enums.IssueStatus;
import lombok.Data;

@Data
public class Issue {
    private final String id;
    private final String subject;
    private final IssueType issueType;
    private final String description;
    private final String txnId;
    private final String clientEmail;
    private String assignedAgentId;
    private IssueStatus issueStatus;
    private String resolution;

    public boolean match(String key, String value) {
        return switch (key) {
            case "id" -> id != null && id.contains(value);
            case "subject" -> subject != null && subject.contains(value);
            case "issueType" -> issueType != null && issueType.name().contains(value);
            case "description" -> description != null && description.contains(value);
            case "txnId" -> txnId != null && txnId.contains(value);
            case "email" -> clientEmail != null && clientEmail.contains(value);
            case "assignedAgentId" -> assignedAgentId != null && assignedAgentId.contains(value);
            case "issueStatus" -> issueStatus != null && issueStatus.name().contains(value);
            case "resolution" -> resolution != null && resolution.contains(value);
            default -> false;
        };
    }
}
