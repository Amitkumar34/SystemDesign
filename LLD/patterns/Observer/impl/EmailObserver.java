package LLD.patterns.Observer.impl;

import LLD.patterns.Observer.Observer;

public class EmailObserver implements Observer {

    @Override
    public void onChange(Object object) {
        System.out.println("Sending email with update: " + object);
    }
}
