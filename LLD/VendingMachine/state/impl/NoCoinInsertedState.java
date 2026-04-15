package LLD.VendingMachine.state.impl;

import LLD.VendingMachine.VendingMachine;
import LLD.VendingMachine.state.State;

public class NoCoinInsertedState implements State {
    private final VendingMachine vendingMachine;

    public NoCoinInsertedState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertAmount(double amount) {
//        vendingMachine.setAmount(amount);
        System.out.println("Amount inserted: " + amount);
    }

    @Override
    public void pressButton(int index) {
        throw new IllegalStateException("Cannot press button in no amount inserted state");
    }

    @Override
    public void dispenseItem(int index) {
        throw new IllegalStateException("Cannot dispense item in no amount inserted state");
    }
}
