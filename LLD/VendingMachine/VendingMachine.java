package LLD.VendingMachine;

import LLD.VendingMachine.state.State;
import LLD.VendingMachine.state.impl.NoCoinInsertedState;
import lombok.Setter;

@Setter
public class VendingMachine {
    private double amount;
    private State currentVMState;
    private int selectedItemIndex;

    public VendingMachine() {
        this.currentVMState = new NoCoinInsertedState(this);
    }

    public void addAmount(double amount){
        this.amount += amount;
    }
}
