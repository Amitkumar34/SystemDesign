package LLD.TaskScheduler.models.impl;

import LLD.TaskScheduler.models.ExecuteContext;
import LLD.TaskScheduler.models.ScheduledTask;

import java.util.Optional;

public class RecurringTask extends ScheduledTask {

    private final long executionTime;
    private final long interval;

    public RecurringTask(ExecuteContext context, long executionTime, long interval) {
        super(context);
        this.executionTime = executionTime;
        this.interval = interval;
    }

    @Override
    public long getNextExecutionTime() {
        return executionTime;
    }

    @Override
    public boolean isRecurring() {
        return true;
    }

    @Override
    public Optional<ScheduledTask> nextScheduledTask() {
        return Optional.of(new RecurringTask(context, executionTime + interval, interval));
    }

    @Override
    public void execute() {
        super.execute();
    }
}
