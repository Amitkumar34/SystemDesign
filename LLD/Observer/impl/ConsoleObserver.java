package LLD.Observer.impl;

import LLD.Observer.Observable;
import LLD.Observer.Observer;

public class ConsoleObserver implements Observer {

    @Override
    public void onChange(Object object) {
        System.out.println("Console update: " + object);
    }
}
