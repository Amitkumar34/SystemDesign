package LLD.AtmMachine.state.impl;

import LLD.AtmMachine.model.Card;
import LLD.AtmMachine.service.AtmMachine;
import LLD.AtmMachine.state.AtmState;

public class IdleAtmState extends AtmState {
    public IdleAtmState(AtmMachine atmMachine) {
        super(atmMachine);
    }

    @Override
    public void insertCard(Card card) {
        atmMachine.setCurrentCard(card);
        setNextState(new CardInsertedState(atmMachine));
    }
}
