package LLD.NotificationService.model.impl;

import LLD.NotificationService.model.IDecorator;
import LLD.NotificationService.model.INotification;

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
