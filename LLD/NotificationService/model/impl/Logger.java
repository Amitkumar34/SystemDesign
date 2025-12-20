package LLD.NotificationService.model.impl;

import LLD.NotificationService.model.IObserver;
import LLD.NotificationService.model.IObserverable;

public class Logger implements IObserver {

    @Override
    public void update(IObserverable observerable) {
        System.out.println("New Noti Log: " + ((NotificationObservable) observerable).getNotificationContent());
    }
}
