package LLD.Observer;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Observable<T> {
    List<Observer> observers;
    @Getter
    T object;

    public Observable(T object) {
        observers = new ArrayList<>();
        this.object = object;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void update(T object) {
        this.object = object;
        notifyAllObservers();
    }

    private void notifyAllObservers() {
        observers.forEach(observer -> observer.onChange(object));
    }
}
