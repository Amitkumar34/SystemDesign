package LLD.CustomerIssueResolution.services;

import LLD.CustomerIssueResolution.enums.IssueType;
import LLD.CustomerIssueResolution.model.Agent;
import LLD.CustomerIssueResolution.repos.AgentRepo;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class AgentService {
    private final AgentRepo agentRepo;

    public Agent addAgent(String email, String name, List<IssueType> expertises) {
        Agent agent = agentRepo.save(
                new Agent("A" + UUID.randomUUID().toString().substring(0, 3), name, email, new HashSet<>(expertises))
        );
        System.out.println(">>> Agent " + agent.getName() + " created");
        return agent;
    }

    public boolean removeAgent(String agentId) {
        return agentRepo.delete(agentId) != null;
    }

    public void viewAgentWorkHistory() {
        agentRepo.getAll().forEach(agent -> System.out.println(agent.getName() + " -> " + agent.getHistory()));
    }
}
