package LLD.CustomerIssueResolution.services;

import LLD.CustomerIssueResolution.enums.IssueStatus;
import LLD.CustomerIssueResolution.model.Agent;
import LLD.CustomerIssueResolution.model.Issue;
import LLD.CustomerIssueResolution.repos.AgentRepo;
import LLD.CustomerIssueResolution.repos.IssueRepo;
import LLD.CustomerIssueResolution.strategy.AssignmentStrategy;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AssignmentService  {
    private final AssignmentStrategy assignmentStrategy;
    private final AgentRepo agentRepo;
    private final IssueRepo issueRepo;

    public void assignAgent(String issueId){
        Issue issue = issueRepo.getById(issueId);
        if(issue ==  null) throw new IllegalArgumentException("Issue not found for assignment");
        Agent agent = assignmentStrategy.assignAgent(issue,agentRepo.getAll().stream().toList());
        if(agent == null) {
            System.out.println(">>> No Agent available");
            return;
        }
        issue.setAssignedAgentId(agent.getId());
        if(agent.isAvailable()){
            agent.setAssignedIssueId(issueId);
            System.out.println(">>> Issue " + issueId + " assigned to " + agent.getName());
        } else {
            agent.getWaitList().add(issueId);
            issue.setIssueStatus(IssueStatus.WAITING);
            System.out.println(">>> Issue " + issueId + " added to waiting list of agent " + agent.getName());
        }
    }
}
