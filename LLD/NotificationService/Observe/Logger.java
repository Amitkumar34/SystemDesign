package LLD.NotificationService.Observe;

import LLD.NotificationService.Notification.INotification;

public class Logger implements IObserver<INotification> {

    @Override
    public void update(INotification notification) {
        System.out.println("New Noti Log: " + notification.getContent());
    }
}
