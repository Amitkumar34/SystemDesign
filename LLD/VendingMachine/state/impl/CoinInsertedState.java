package LLD.VendingMachine.state.impl;

import LLD.VendingMachine.VendingMachine;
import LLD.VendingMachine.state.State;

public class CoinInsertedState implements State {
    private final VendingMachine vendingMachine;

    public CoinInsertedState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertAmount(double amount) {
        vendingMachine.addAmount(amount);
        System.out.println("Amount added: " + amount);
    }

    @Override
    public void pressButton(int index) {
        vendingMachine.setCurrentVMState(new DispenseState(vendingMachine));
        vendingMachine.setSelectedItemIndex(index);
        System.out.println("Item Selected: " + index);
    }

    @Override
    public void dispenseItem(int index) {
        throw new IllegalStateException("Cannot dispense item as no item is selected");
    }
}
