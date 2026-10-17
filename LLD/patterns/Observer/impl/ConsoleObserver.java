package LLD.patterns.Observer.impl;

import LLD.patterns.Observer.Observer;

public class ConsoleObserver implements Observer {

    @Override
    public void onChange(Object object) {
        System.out.println("Console update: " + object);
    }
}
