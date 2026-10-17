package LLD.NotificationService.Decorator;

import LLD.NotificationService.Notification.INotification;

import java.util.Calendar;


public class TimeStampDecorator extends IDecorator {

    public TimeStampDecorator(INotification notification) {
        super(notification);
    }

    @Override
    public String getContent() {
         return notification.getContent() + Calendar.getInstance().getTimeInMillis();
    }
}
