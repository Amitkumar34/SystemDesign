package LLD.CustomerIssueResolution.services;

import LLD.CustomerIssueResolution.enums.IssueStatus;
import LLD.CustomerIssueResolution.enums.IssueType;
import LLD.CustomerIssueResolution.model.Agent;
import LLD.CustomerIssueResolution.model.Issue;
import LLD.CustomerIssueResolution.repos.AgentRepo;
import LLD.CustomerIssueResolution.repos.IssueRepo;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@RequiredArgsConstructor
public class IssueService {
    private final IssueRepo issueRepo;
    private final AgentRepo agentRepo;

    public Issue createIssue(String txnId, IssueType issueType, String subject, String description, String email) {
        Issue issue = new Issue("I" + (UUID.randomUUID().toString().substring(0,3)), subject, issueType, description, txnId, email);
        issueRepo.save(issue);
        System.out.println(">>> Issue " + issue.getId() + " created");
        return issue;
    }

    public Collection<Issue> getIssues(Map<String, ?> filter) {
        return issueRepo.getAll().stream().filter(issue -> {
            AtomicBoolean match = new AtomicBoolean(false);
            filter.forEach((k, v) -> {
                if (issue.match(k, v.toString())) match.set(true);
            });
            return match.get();
        }).toList();
    }

    public void updateIssue(
            String id, IssueStatus status, String resolution) {
        Issue issue = issueRepo.getById(id);
        if(issue == null) throw new IllegalArgumentException("Issue not found");
        issue.setIssueStatus(status);
        issue.setResolution(resolution);
        System.out.println(">>> " + id + " status updated to " + status);
    }

    public void resolveIssue(String id, String resolution) {
        Issue issue = issueRepo.getById(id);
        if(issue == null) throw new IllegalArgumentException("Issue not found");
        issue.setIssueStatus(IssueStatus.RESOLVED);
        issue.setResolution(resolution);
        if(issue.getAssignedAgentId() != null){
            Agent agent = agentRepo.getById(issue.getAssignedAgentId());
            if(agent != null){
                agent.getHistory().add(issue.getId());
                agent.setAssignedIssueId(null);
            }
        }
        System.out.println(">>> " + id + "marked as Resoled");
    }
}
