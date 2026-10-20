package LLD.AtmMachine.state.impl;

import LLD.AtmMachine.enums.AtmOption;
import LLD.AtmMachine.service.AtmMachine;
import LLD.AtmMachine.state.AtmState;

import java.util.Arrays;

public class CardInsertedState extends AtmState {
    public CardInsertedState(AtmMachine atmMachine) {
        super(atmMachine);
    }

    @Override
    public void enterPin(String pin) {
        if (!this.atmMachine.getCurrentCard().getPin().equals(pin)) {
            System.out.println("Invalid pin !!!");
            return;
        }
        System.out.println("Authenticated !.\n Select option: \n" + Arrays.toString(AtmOption.values()));
        setNextState(new AuthenticatedState(atmMachine));
    }
}