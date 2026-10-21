package LLD.AtmMachine.state.impl;

import LLD.AtmMachine.enums.AtmOption;
import LLD.AtmMachine.service.AtmMachine;
import LLD.AtmMachine.state.AtmState;

public class AuthenticatedState extends AtmState {
    public AuthenticatedState(AtmMachine atmMachine) {
        super(atmMachine);
    }

    @Override
    public void selectOption(AtmOption option) {
        switch (option) {
            case WITHDRAW -> setNextState(new CashDispenseState(atmMachine, atmMachine.getCashDispenser()));
            default -> throw new IllegalArgumentException("Invalid Options selected!");
        }
    }
}
