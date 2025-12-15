package LLD.NotificationService.model.impl;

import LLD.NotificationService.model.INotificationStrategy;

public class SMSNotificationStrategy implements INotificationStrategy {
    String mobileNumber;

    public SMSNotificationStrategy(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public void sendNotification(String content) {
        System.out.println("Sending Notification to mobileNumber: " + mobileNumber + " content: " + content);
    }
}
