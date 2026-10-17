package LLD.CustomerIssueResolution.repos;

import LLD.CustomerIssueResolution.model.Agent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AgentRepo implements Repository<String, Agent> {
    private final Map<String, Agent> agents = new HashMap<>();

    @Override
    public Agent save(Agent agent) {
        agents.put(agent.getId(),agent);
        return agent;
    }

    @Override
    public Agent delete(String id) {
        return agents.remove(id);
    }

    @Override
    public Agent getById(String id) {
        return agents.get(id);
    }

    @Override
    public Collection<Agent> getAll() {
        return agents.values();
    }
}
