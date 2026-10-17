package LLD.NotificationService;

import LLD.NotificationService.Decorator.SignatureDecorator;
import LLD.NotificationService.Decorator.TimeStampDecorator;
import LLD.NotificationService.NotiStrategy.EmailNotificationStategy;
import LLD.NotificationService.NotiStrategy.SMSNotificationStrategy;
import LLD.NotificationService.Notification.INotification;
import LLD.NotificationService.Notification.SimpleNotification;
import LLD.NotificationService.Observe.Logger;
import LLD.NotificationService.Observe.NotificationEngine;
import LLD.NotificationService.Service.NotificationService;

public class NotificationServiceClient {
    public static void main(String[] args) {

        System.out.println("\n\nNotification Service\n\n");
        //Logging, SMS,EMAIl
        Logger logger = new Logger();
        NotificationEngine notificationEngine = new NotificationEngine();
        notificationEngine.addNotificationStrategy(new EmailNotificationStategy("a@g.com"));
        notificationEngine.addNotificationStrategy(new SMSNotificationStrategy("1234"));

        // Creating service
        NotificationService notificationService = NotificationService.getInstance();
        notificationService.addObserver(logger);
        notificationService.addObserver(notificationEngine);

        // Send Notification
        INotification notification = new SimpleNotification("main content");
        notification = new SignatureDecorator(notification, "Amit");

        notificationService.sendNotification(notification);
        notification = new TimeStampDecorator(notification);
        notificationService.sendNotification(notification);

        System.out.println("--------Printing inputs -----------");
        notificationService.notifications.forEach(e -> System.out.println(e.getContent()));
    }
}
