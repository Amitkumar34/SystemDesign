package LLD.TaskScheduler;

import LLD.TaskScheduler.models.ExecuteContext;
import LLD.TaskScheduler.models.ScheduledTask;
import LLD.TaskScheduler.models.impl.OneTimeTask;
import LLD.TaskScheduler.models.impl.RecurringTask;
import LLD.TaskScheduler.scheduler.TaskScheduler;
import LLD.TaskScheduler.store.impl.PriorityBlockingQueueTaskStore;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Comparator;

public class TaskSchedulerDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== TaskScheduler Demo ===\n");

        // Create task store with comparator ordering by next execution time
        Comparator<ScheduledTask> comparator = Comparator.comparingLong(ScheduledTask::getNextExecutionTime);
        PriorityBlockingQueueTaskStore taskStore = new PriorityBlockingQueueTaskStore(comparator, 10);

        // Create scheduler with 2 worker threads
        TaskScheduler scheduler = new TaskScheduler(2, taskStore);

        long now = System.currentTimeMillis();

        // One-time task: runs after 1 second
        ExecuteContext oneTimeContext = () -> 
            System.out.println("[" + LocalTime.now() + "] One-time task executed!");
        taskStore.add(new OneTimeTask(oneTimeContext, now + 1000));

        // One-time task: runs after 3 seconds
        ExecuteContext delayedContext = () -> 
            System.out.println("[" + LocalTime.now() + "] Delayed one-time task executed!");
        taskStore.add(new OneTimeTask(delayedContext, now + 3000));

        // Recurring task: runs every 2 seconds, starting after 500ms
        ExecuteContext recurringContext = () -> 
            System.out.println("[" + LocalTime.now() + "] Recurring task executed!");
        taskStore.add(new RecurringTask(recurringContext, now + 500, 2000));

        // Another recurring task: runs every 3 seconds, starting after 1.5 seconds
        ExecuteContext heartbeatContext = () -> 
            System.out.println("[" + LocalTime.now() + "] ❤️ Heartbeat!");
        taskStore.add(new RecurringTask(heartbeatContext, now + 1500, 3000));

//        LocalTime startTime = LocalTime.ofInstant(Instant.ofEpochMilli(now), ZoneId.systemDefault());
        System.out.println("[" + LocalTime.now() + "] Tasks scheduled. Running for 10 seconds...\n");

        // Let tasks run for 10 seconds
        Thread.sleep(10000);

        // Stop the scheduler
        System.out.println("\n[" + LocalTime.now() + "] Stopping scheduler...");
        scheduler.stop();
        System.out.println("[" + LocalTime.now() + "] Scheduler stopped. Goodbye!");
    }
}

