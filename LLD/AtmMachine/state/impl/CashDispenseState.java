package LLD.AtmMachine.state.impl;

import LLD.AtmMachine.CashDispenser;
import LLD.AtmMachine.service.AtmMachine;
import LLD.AtmMachine.state.AtmState;

import java.util.Map;

public class CashDispenseState extends AtmState {
    private final CashDispenser cashDispenser;

    public CashDispenseState(AtmMachine atmMachine, CashDispenser cashDispenser) {
        super(atmMachine);
        this.cashDispenser = cashDispenser;
    }

    @Override
    public void dispenseCash(double amount) {
        Map<Integer, Integer> cashMap = cashDispenser.canDispense(atmMachine.getAtm(), amount);
        if (cashMap != null) {
            String output = cashDispenser.dispense(atmMachine.getAtm(), cashMap);
            System.out.println("Dispensed ₹" + amount + " : " + output.substring(0, output.length() - 2));
        } else {
            System.out.println("Unavailable to dispense given amount");
        }
    }
}
