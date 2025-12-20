package LLD.TaskScheduler.models;

import LLD.TaskScheduler.store.TaskStore;

public class TaskRunner implements Runnable {
    private TaskStore<ScheduledTask> taskStore;
    private volatile boolean running;

    public TaskRunner(TaskStore<ScheduledTask> taskStore) {
        this.taskStore = taskStore;
    }

    @Override
    public void run() {
        this.running = true;
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                ScheduledTask scheduledTask = taskStore.poll(); // Waits till it get the task
                long delay = scheduledTask.getNextExecutionTime() - System.currentTimeMillis();
                if (delay > 0) {
                    taskStore.add(scheduledTask);
                    synchronized (this) {
                        wait(delay);
                    }
                } else {
                    scheduledTask.execute();
                    if (scheduledTask.isRecurring()) {
                        taskStore.add(scheduledTask.nextScheduledTask().get());
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        this.running = false;
        synchronized (this) {
            notify();
        }
    }
}
