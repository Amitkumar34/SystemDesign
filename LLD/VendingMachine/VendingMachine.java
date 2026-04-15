package LLD.VendingMachine;

import LLD.VendingMachine.state.State;
import LLD.VendingMachine.state.impl.NoCoinInsertedState;

public class VendingMachine {
    private double amount;
    private State currentVMState;
    private State noCoinInsertedState,coinInsertedState,dispensateState;

    public VendingMachine() {
        this.noCoinInsertedState = new NoCoinInsertedState(this);
    }
}
