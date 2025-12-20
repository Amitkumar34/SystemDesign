package LLD.NotificationService.model.impl;

import LLD.NotificationService.model.INotification;
import LLD.NotificationService.model.IObserver;
import LLD.NotificationService.model.IObserverable;

import java.util.ArrayList;
import java.util.List;

public class NotificationObservable implements IObserverable {

    List<IObserver> observers;
    INotification notification;

    public NotificationObservable() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver e : observers) e.update(this);
    }

    public INotification getNotification() {
        return notification;
    }


    public String getNotificationContent() {
        return notification.getContent();
    }

    public void setNotification(INotification notification) {
        this.notification = notification;
        notifyObservers();
    }
}
