package LLD.NotificationService.Observe;

public interface IObserver<T> {
    void update(T object);
}
