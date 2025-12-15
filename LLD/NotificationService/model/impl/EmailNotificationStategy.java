package LLD.NotificationService.model.impl;

import LLD.NotificationService.model.INotificationStrategy;

public class EmailNotificationStategy implements INotificationStrategy {
    String emailId;

    public EmailNotificationStategy(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public void sendNotification(String content) {
        System.out.println("Sending Notification to email: " + emailId + " content: " + content);
    }
}