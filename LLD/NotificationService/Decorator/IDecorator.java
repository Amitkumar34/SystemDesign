package LLD.NotificationService.Decorator;

import LLD.NotificationService.Notification.INotification;

public abstract class IDecorator implements INotification {
    protected INotification notification;

    public IDecorator(INotification notification) {
        this.notification = notification;
    }
}
