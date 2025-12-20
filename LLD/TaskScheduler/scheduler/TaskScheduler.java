package LLD.TaskScheduler.scheduler;

import LLD.TaskScheduler.models.ScheduledTask;
import LLD.TaskScheduler.models.TaskRunner;
import LLD.TaskScheduler.store.TaskStore;

import java.util.ArrayList;
import java.util.List;

public class TaskScheduler {
    private final List<Thread> threads;
    private final List<TaskRunner> runners;
    private TaskStore<ScheduledTask> taskStore;

    public TaskScheduler(int noOfThread, TaskStore<ScheduledTask> taskStore) {
        this.threads = new ArrayList<>();
        this.runners = new ArrayList<>();
        this.taskStore = taskStore;
        for (int i = 0; i < noOfThread; i++) {
            TaskRunner tr = new TaskRunner(taskStore);
            Thread th = new Thread(tr);
            th.start();
            threads.add(th);
            runners.add(tr);
        }
    }

    public void stop() {
        runners.forEach(TaskRunner::stop);
        threads.forEach(t -> {
            t.interrupt();
            try {
                t.join(); // wait for the thread to finish
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
