package LLD.NotificationService.model.impl;

import LLD.NotificationService.model.INotification;

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
