package LLD.NotificationService.Notification;

public class SimpleNotification implements INotification {
    String text;

    public SimpleNotification(String text) {
        this.text = text;
    }

    @Override
    public String getContent() {
        return text;
    }
}
