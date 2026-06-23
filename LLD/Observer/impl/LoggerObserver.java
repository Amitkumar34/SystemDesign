package LLD.Observer.impl;

import LLD.Observer.Observable;
import LLD.Observer.Observer;

public class LoggerObserver implements Observer {

    @Override
    public void onChange(Object object) {
        System.out.println("Log entry: " + object);
    }
}
