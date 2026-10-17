package LLD.CustomerIssueResolution;

import LLD.CustomerIssueResolution.enums.IssueStatus;
import LLD.CustomerIssueResolution.enums.IssueType;
import LLD.CustomerIssueResolution.model.Issue;
import LLD.CustomerIssueResolution.repos.AgentRepo;
import LLD.CustomerIssueResolution.repos.IssueRepo;
import LLD.CustomerIssueResolution.services.AgentService;
import LLD.CustomerIssueResolution.services.AssignmentService;
import LLD.CustomerIssueResolution.services.IssueService;
import LLD.CustomerIssueResolution.strategy.DefaultAssignmentStrategy;

import java.util.List;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        AgentRepo agentRepo = new AgentRepo();
        IssueRepo issueRepo = new IssueRepo();
        AssignmentService assignmentService = new AssignmentService(new DefaultAssignmentStrategy(), agentRepo, issueRepo);
        IssueService issueService = new IssueService(issueRepo, agentRepo);
        AgentService agentService = new AgentService(agentRepo);

        Issue i1 = issueService.createIssue("T1", IssueType.PAYMENT_RELATED,
                "Payment failed", "Payment of 100rs fail, please refund","user1@email.com");
        Issue i2 = issueService.createIssue("T2", IssueType.MF_RELATED,
                "MF not showing", "Payment success, MF not assigned","user2@email.com");
        Issue i3 = issueService.createIssue("T3", IssueType.INSURANCE_RELATED,
                "Insurance claim issue", "Need help in insurance claim","user3@email.com");
        Issue i4 = issueService.createIssue("T4", IssueType.GOLD_RELATED,
                "Insurance claim issue", "Need help in insurance claim","user3@email.com");

        agentService.addAgent("agent1@xyz.com","Agent 1", List.of(IssueType.INSURANCE_RELATED,IssueType.MF_RELATED));
        agentService.addAgent("agent2@xyz.com","Agent 2", List.of(IssueType.PAYMENT_RELATED,IssueType.MF_RELATED));

        assignmentService.assignAgent(i1.getId());
        assignmentService.assignAgent(i2.getId());
        assignmentService.assignAgent(i3.getId());
        assignmentService.assignAgent(i4.getId());

        System.out.println("\n--- Issues for user1 -----");
        issueService.getIssues(Map.of("email","user1")).forEach(System.out::println);
        System.out.println("\n--- Issues for user2 -----");
        issueService.getIssues(Map.of("email","user2")).forEach(System.out::println);
        System.out.println("\n--- Issues for user3 -----");
        issueService.getIssues(Map.of("email","user3")).forEach(System.out::println);
        System.out.println("\n--- Issues for MF -----");
        issueService.getIssues(Map.of("issueType","MF")).forEach(System.out::println);

        issueService.updateIssue(i2.getId(), IssueStatus.IN_PROGRESS, "waiting for SEBI response");
        issueService.resolveIssue(i2.getId(), "MF is now assigned");

        issueService.updateIssue(i3.getId(), IssueStatus.IN_PROGRESS, "Claim in processing");
        issueService.updateIssue(i3.getId(), IssueStatus.RESOLVED, "Claim rejected");

        System.out.println("\n--- Agents work history -----");
        agentService.viewAgentWorkHistory();
    }
}
