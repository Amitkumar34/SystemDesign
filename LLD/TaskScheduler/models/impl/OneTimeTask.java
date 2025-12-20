package LLD.TaskScheduler.models.impl;

import LLD.TaskScheduler.models.ExecuteContext;
import LLD.TaskScheduler.models.ScheduledTask;

import java.util.Optional;

public class OneTimeTask extends ScheduledTask {

    private final long executionTime;

    public OneTimeTask(ExecuteContext context, long executionTime) {
        super(context);
        this.executionTime = executionTime;
    }

    @Override
    public boolean isRecurring() {
        return false;
    }

    @Override
    public Optional<ScheduledTask> nextScheduledTask() {
        return Optional.empty();
    }

    @Override
    public long getNextExecutionTime() {
        return executionTime;
    }
}
