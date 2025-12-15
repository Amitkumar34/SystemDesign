package LLD.NotificationService.model;

public interface IObserverable {
    void addObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers();
}
