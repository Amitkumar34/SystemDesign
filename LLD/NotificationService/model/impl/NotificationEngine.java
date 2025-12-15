package LLD.NotificationService.model.impl;

import LLD.NotificationService.model.INotificationStrategy;
import LLD.NotificationService.model.IObserver;
import LLD.NotificationService.model.IObserverable;

import java.util.ArrayList;
import java.util.List;

public class NotificationEngine implements IObserver {
    List<INotificationStrategy> notificationStrategyList;

    public NotificationEngine() {
        this.notificationStrategyList = new ArrayList<>();
    }

    public void addNotificationStrategy(INotificationStrategy strategy) {
        notificationStrategyList.add(strategy);
    }

    @Override
    public void update(IObserverable observerable) {
        for (INotificationStrategy iNotificationStrategy : notificationStrategyList) {
            iNotificationStrategy.sendNotification(((NotificationObservable) observerable).getNotificationContent());
        }
    }
}
