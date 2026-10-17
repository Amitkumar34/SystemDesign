package LLD.Logger.handler.impl;

import LLD.Logger.LogLevel;
import LLD.Logger.LogSinkSubject;
import LLD.Logger.handler.LoggerHandler;

public class DebugLogger extends LoggerHandler {

    public DebugLogger(LogLevel level) {
        super(level);
    }

    public void publishLog(String message, LogSinkSubject logSinkSubject) {
        String msg = "Debug: " + message;
        logSinkSubject.notifyObservers(LogLevel.DEBUG, msg);
    }
}
