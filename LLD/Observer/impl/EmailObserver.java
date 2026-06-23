package LLD.Observer.impl;

import LLD.Observer.Observable;
import LLD.Observer.Observer;

public class EmailObserver implements Observer {

    @Override
    public void onChange(Object object) {
        System.out.println("Sending email with update: " + object);
    }
}
