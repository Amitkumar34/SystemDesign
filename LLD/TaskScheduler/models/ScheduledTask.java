package LLD.TaskScheduler.models;

import java.util.Optional;

public abstract class ScheduledTask {
    protected final ExecuteContext context;

    public ScheduledTask(ExecuteContext context) {
        this.context = context;
    }

    public abstract boolean isRecurring();

    public void execute() {
        context.execute();
    }

    public abstract Optional<ScheduledTask> nextScheduledTask();

    public abstract long getNextExecutionTime();
}
