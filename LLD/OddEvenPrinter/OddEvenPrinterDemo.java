package LLD.OddEvenPrinter;

public class OddEvenPrinterDemo {
    public static void main(String[] args) throws InterruptedException {
        OddEvenPrinter printer = new OddEvenPrinter(10);

        Thread odd = new Thread(() -> printer.print(true), "odd");
        Thread even = new Thread(() -> printer.print(false), "even");

        odd.start();
        even.start();

        odd.join();
        even.join();
    }
}
