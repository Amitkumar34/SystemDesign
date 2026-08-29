package LLD.NotificationService.Decorator;

import LLD.NotificationService.Notification.INotification;

public class SignatureDecorator extends IDecorator {
    String signature;
    public SignatureDecorator(INotification notification,String signature) {
        super(notification);
        this.signature = signature;
    }

    @Override
    public String getContent() {
        return notification.getContent() + " -- " + signature + " --";
    }
}
