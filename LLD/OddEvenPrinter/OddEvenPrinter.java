package LLD.OddEvenPrinter;

public class OddEvenPrinter {
    private int counter = 1;
    private final int limit;

    public OddEvenPrinter(int limit) {
        this.limit = limit;
    }

    public synchronized void print(boolean isOddThread) {
        while (counter <= limit) {
            boolean isOddNumber = (counter % 2 != 0);
            if (isOddNumber == isOddThread) {
                print(counter);
                counter++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
        notifyAll();
    }

    private void print(int value) {
        System.out.println(Thread.currentThread().getName() + " :" + value);
    }
}
