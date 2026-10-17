package LLD.CustomerIssueResolution.strategy;

import LLD.CustomerIssueResolution.model.Agent;
import LLD.CustomerIssueResolution.model.Issue;

import java.util.List;

public interface AssignmentStrategy {
    Agent assignAgent(Issue issue, List<Agent> agentList);
}
