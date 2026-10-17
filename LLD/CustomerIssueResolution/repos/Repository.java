package LLD.CustomerIssueResolution.repos;

import java.util.Collection;

public interface Repository<I,T> {
    T save(T item);
    T delete(I id);
    T getById(I id);
    Collection<T> getAll();
}
