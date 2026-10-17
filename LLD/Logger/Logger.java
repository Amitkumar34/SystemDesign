package LLD.Logger;

import LLD.Logger.handler.LoggerHandler;

/**
 * Singleton Class
 */
public class Logger {

    private volatile static Logger logger;
    private final LoggerHandler loggerHandler;
    private final LogSinkSubject logSinkSubject;


    private Logger() {
        if (logger != null)
            throw new IllegalStateException("Object Already Created");
        loggerHandler = LogManager.buildLoggerChain();
        logSinkSubject = LogManager.buildLogSinkSubject();
    }

    public static Logger getInstance() {
        if (logger == null) {
            synchronized (Logger.class) {
                if (logger == null) {
                    logger = new Logger();
                }
            }
        }
        return logger;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    protected Object readResolve() {
        return logger;
    }


    private void logMessage(LogLevel level, String message) {
        loggerHandler.log(level, message, logSinkSubject);
    }

    public void info(String message) {
        logMessage(LogLevel.INFO, message);
    }

    public void error(String message) {
        logMessage(LogLevel.ERROR, message);
    }

    public void debug(String message) {
        logMessage(LogLevel.DEBUG, message);
    }
}
