package LLD.Logger.handler.impl;

import LLD.Logger.LogLevel;
import LLD.Logger.LogSinkSubject;
import LLD.Logger.handler.LoggerHandler;

public class InfoLogger extends LoggerHandler {

    public InfoLogger(LogLevel level) {
        super(level);
    }

    public void publishLog(String message, LogSinkSubject logSinkSubject) {
        String msg = "Info: " + message;
        logSinkSubject.notifyObservers(LogLevel.INFO, msg);
    }
}
