package LLD.NotificationService.Observe;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class Observerable<T> implements IObservable {
    private List<IObserver<T>> observers;
    private T observedObject;

    public Observerable(T observedObject) {
        this.observedObject = observedObject;
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(e -> e.update(observedObject));
    }

    public T get() {
        return observedObject;
    }

    public void set(T observedObject) {
        this.observedObject = observedObject;
        notifyObservers();
    }
}
