package LLD.Observer;

import LLD.Observer.impl.ConsoleObserver;
import LLD.Observer.impl.EmailObserver;
import LLD.Observer.impl.LoggerObserver;

public class Demo {

    public static void main(String[] args) {
        Observable<String> observable = new Observable<>("Initial state");

        observable.addObserver(new ConsoleObserver());
        observable.addObserver(new EmailObserver());
        LoggerObserver loggerObserver = new LoggerObserver();
        observable.addObserver(loggerObserver);

        observable.update("State changed");

        observable.removeObserver(loggerObserver);
        observable.addObserver(object -> System.out.println("Custom with update: " + object));

        observable.update("State changed: Removed Logger Observer");
    }
}
