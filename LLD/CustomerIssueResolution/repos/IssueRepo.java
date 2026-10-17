package LLD.CustomerIssueResolution.repos;

import LLD.CustomerIssueResolution.model.Issue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class IssueRepo implements Repository<String,Issue>{
    private final Map<String, Issue> issues = new HashMap<>();

    @Override
    public Issue save(Issue issue) {
        issues.put(issue.getId(),issue);
        return issue;
    }

    @Override
    public Issue delete(String id) {
        return issues.remove(id);
    }

    @Override
    public Issue getById(String id) {
        return issues.get(id);
    }

    @Override
    public Collection<Issue> getAll() {
        return issues.values();
    }
}


