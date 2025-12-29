package LLD.TaskScheduler.scheduler;

import LLD.TaskScheduler.models.ScheduledTask;
import LLD.TaskScheduler.store.TaskStore;

import java.util.concurrent.*;

public class TaskSchedulerV3 {
    private final TaskStore<ScheduledTask> taskStore;
    private final ScheduledExecutorService scheduler;
    private final ExecutorService executor;
    private volatile boolean running;

    public TaskSchedulerV3(int noOfThreads, TaskStore<ScheduledTask> taskStore) {
        this.taskStore = taskStore;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.executor = Executors.newFixedThreadPool(noOfThreads);
        this.running = true;
        
        // Start polling for tasks
        scheduler.submit(this::pollAndSchedule);
    }

    private void pollAndSchedule() {
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                ScheduledTask task = taskStore.poll();
                long delay = task.getNextExecutionTime() - System.currentTimeMillis();
                
                if (delay > 0) {
                    // Schedule for later
                    scheduler.schedule(() -> executeTask(task), delay, TimeUnit.MILLISECONDS);
                } else {
                    // Execute immediately
                    executeTask(task);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void executeTask(ScheduledTask task) {
        executor.submit(() -> {
            task.execute();
            if (task.isRecurring()) {
                task.nextScheduledTask().ifPresent(taskStore::add);
            }
        });
    }

    public void stop() {
        running = false;
        scheduler.shutdownNow();
        executor.shutdownNow();
    }
}

