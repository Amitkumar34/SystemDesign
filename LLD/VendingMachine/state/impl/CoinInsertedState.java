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
//        vendingMachine.addAmount(amount);
        System.out.println("Amount added: " + amount);
    }

    @Override
    public void pressButton(int index) {
//        vendinx
    }

    @Override
    public void dispenseItem(int index) {

    }
}
