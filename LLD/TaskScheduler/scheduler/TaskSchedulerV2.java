package LLD.TaskScheduler.scheduler;

import LLD.TaskScheduler.models.ScheduledTask;
import LLD.TaskScheduler.models.TaskRunner;
import LLD.TaskScheduler.store.TaskStore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TaskSchedulerV2 {
    private TaskStore<ScheduledTask> taskStore;
    private ExecutorService executorService;
    private final List<TaskRunner> runners;

    public TaskSchedulerV2(int noOfThread, TaskStore<ScheduledTask> taskStore) {
        this.taskStore = taskStore;
        this.executorService = Executors.newFixedThreadPool(noOfThread);
        this.runners = new ArrayList<>();
        for (int i = 0; i < noOfThread; i++) {
            TaskRunner tr = new TaskRunner(taskStore);
            runners.add(tr);
            executorService.submit(tr);
        }
    }
    public void stop() {
        // Stop all runners
        runners.forEach(TaskRunner::stop);

        // Shutdown executor gracefully
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
