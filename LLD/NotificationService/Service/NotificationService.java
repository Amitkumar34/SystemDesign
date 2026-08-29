package LLD.NotificationService.Service;

import LLD.NotificationService.Notification.INotification;
import LLD.NotificationService.Observe.IObserver;
import LLD.NotificationService.Observe.Observerable;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private static NotificationService notificationService;
    public List<INotification> notifications = new ArrayList<>();
    private Observerable<INotification> notificationObserverable;

    private NotificationService (){
        notificationObserverable = new Observerable<>(null);
    }

    public void addObserver(IObserver observer){
        notificationObserverable.addObserver(observer);
    }


    public static NotificationService getInstance(){
        if(notificationService == null)
            notificationService = new NotificationService();
        return notificationService;
    }

    public void sendNotification(INotification notification){
        notifications.add(notification);
        notificationObserverable.set(notification);
    }
}
