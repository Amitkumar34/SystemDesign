package LLD.CustomerIssueResolution.strategy;

import LLD.CustomerIssueResolution.model.Agent;
import LLD.CustomerIssueResolution.model.Issue;

import java.util.List;

public class DefaultAssignmentStrategy implements AssignmentStrategy {
    @Override
    public Agent assignAgent(Issue issue, List<Agent> agents) {
        for (Agent agent : agents) {
            if(agent.isAvailable() && agent.getExpertises().contains(issue.getIssueType())) return agent;
        }
        for (Agent agent : agents) {
            if(agent.getExpertises().contains(issue.getIssueType())) return agent;
        }
        return null;
    }
}
