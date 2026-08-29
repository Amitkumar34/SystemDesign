package LLD.Logger.handler;


import LLD.Logger.LogLevel;
import LLD.Logger.LogSinkSubject;
import lombok.Setter;

public abstract class LoggerHandler {
    @Setter
    private LoggerHandler nextLoggerHandler;
    protected LogLevel logLevel;

    protected LoggerHandler(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public void log(LogLevel level, String message, LogSinkSubject logSinkSubject) {
        if (this.logLevel.getLevel() == level.getLevel()) {
            publishLog(message, logSinkSubject);
        }

        if (nextLoggerHandler != null) {
            nextLoggerHandler.log(level, message, logSinkSubject);
        }
    }

    protected abstract void publishLog(String message, LogSinkSubject logSinkSubject);
}
