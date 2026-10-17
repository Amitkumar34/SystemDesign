package LLD.patterns.Observer.impl;

import LLD.patterns.Observer.Observer;

public class LoggerObserver implements Observer {

    @Override
    public void onChange(Object object) {
        System.out.println("Log entry: " + object);
    }
}
