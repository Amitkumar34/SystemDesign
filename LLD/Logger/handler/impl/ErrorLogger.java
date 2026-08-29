package LLD.Logger.handler.impl;

import LLD.Logger.LogLevel;
import LLD.Logger.LogSinkSubject;
import LLD.Logger.handler.LoggerHandler;

public class ErrorLogger extends LoggerHandler {

    public ErrorLogger(LogLevel level) {
        super(level);
    }

    public void publishLog(String message, LogSinkSubject logSinkSubject) {
        String msg = "Error: " + message;
        logSinkSubject.notifyObservers(LogLevel.ERROR, msg);
    }
}
