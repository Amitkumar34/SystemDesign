package LLD.TaskScheduler.store.impl;

import LLD.TaskScheduler.models.ScheduledTask;
import LLD.TaskScheduler.store.TaskStore;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueTaskStore implements TaskStore<ScheduledTask> {
    private final PriorityBlockingQueue<ScheduledTask> taskQueue;
    private final Set<ScheduledTask> tasks;

    public PriorityBlockingQueueTaskStore(Comparator<ScheduledTask> comparator, int capacity) {
        super();
        taskQueue = new PriorityBlockingQueue<>(capacity, comparator);
        tasks = new HashSet<>();
    }

    @Override
    public ScheduledTask peek() {
        return taskQueue.peek();
    }

    @Override
    public boolean add(ScheduledTask task) {
        return taskQueue.offer(task);
    }

    @Override
    public boolean remove(ScheduledTask task) {
        if (tasks.contains(task)) {
            return taskQueue.remove(task);
        } else {
            return false;
        }
    }

    @Override
    public ScheduledTask poll() throws InterruptedException {
        return taskQueue.take();
    }

    @Override
    public boolean isEmpty() {
        return taskQueue.isEmpty();
    }
}

// threads, taskrunners, taskscheduler