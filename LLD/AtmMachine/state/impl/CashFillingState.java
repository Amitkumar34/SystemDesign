package LLD.AtmMachine.state.impl;

import LLD.AtmMachine.service.AtmMachine;
import LLD.AtmMachine.state.AtmState;

import java.util.Map;

public class CashFillingState extends AtmState {
    public CashFillingState(AtmMachine atmMachine) {
        super(atmMachine);
    }

    public void addAmount(Map<Integer, Integer> cash) {
        if (cash != null)
            atmMachine.getAtm().addAmount(cash);
        setNextState(new IdleAtmState(atmMachine));
    }
}
