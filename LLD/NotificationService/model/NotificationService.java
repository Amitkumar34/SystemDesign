package LLD.NotificationService.model;

import LLD.NotificationService.model.impl.NotificationObservable;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private static NotificationService notificationService;
    public List<INotification> notifications = new ArrayList<>();
    NotificationObservable observerable = new NotificationObservable();

    private NotificationService (){

    }

    public void addObserver(IObserver observer){
        observerable.addObserver(observer);
    }


    public static NotificationService getInstance(){
        if(notificationService == null)
            notificationService = new NotificationService();
        return notificationService;
    }

    public void sendNotification(INotification notification){
        notifications.add(notification);
        observerable.setNotification(notification);
    }

}
