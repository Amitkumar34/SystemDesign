package LLD.TaskScheduler.store;

import LLD.TaskScheduler.models.ScheduledTask;

public interface TaskStore<T extends ScheduledTask> {

    T peek();

    T poll() throws InterruptedException;

    boolean add(T task);

    boolean remove(T task);

    boolean isEmpty();
}
