package LLD.VendingMachine.state;

public interface State {
    void insertAmount(double amount);

    void pressButton(int index);

    void dispenseItem(int index);
}
