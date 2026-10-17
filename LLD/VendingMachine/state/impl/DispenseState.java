package LLD.VendingMachine.state.impl;

import LLD.VendingMachine.VendingMachine;
import LLD.VendingMachine.state.State;

public class DispenseState implements State {
    private VendingMachine vendingMachine;

    public DispenseState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertAmount(double amount) {
        throw new IllegalStateException("Cannot insert amount in dispense state");
    }

    @Override
    public void pressButton(int index) {
        throw new IllegalStateException("Cannot select item in dispense state");
    }

    @Override
    public void dispenseItem(int index) {
        System.out.println("Dispensing Item at index = " + index);
        vendingMachine.setSelectedItemIndex(-1);
        vendingMachine.setAmount(0);
        vendingMachine.setCurrentVMState(new NoCoinInsertedState(vendingMachine));
    }
}