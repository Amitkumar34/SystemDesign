package LLD.NotificationService.Observe;

import LLD.NotificationService.NotiStrategy.INotificationStrategy;
import LLD.NotificationService.Notification.INotification;

import java.util.ArrayList;
import java.util.List;

public class NotificationEngine implements IObserver<INotification> {
    List<INotificationStrategy> notificationStrategyList;

    public NotificationEngine() {
        this.notificationStrategyList = new ArrayList<>();
    }

    public void addNotificationStrategy(INotificationStrategy strategy) {
        notificationStrategyList.add(strategy);
    }

    @Override
    public void update(INotification notification) {
        for (INotificationStrategy iNotificationStrategy : notificationStrategyList) {
            iNotificationStrategy.sendNotification(notification.getContent());
        }
    }
}
