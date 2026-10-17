package LLD.Logger.observer.impl;

import LLD.Logger.observer.LogObserver;

public class FileLogger implements LogObserver {

    public void log(String message) {
        System.out.println("Logging into file: " + message);
    }
}
