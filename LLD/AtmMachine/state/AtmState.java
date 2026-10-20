package LLD.AtmMachine.state;

import LLD.AtmMachine.enums.AtmOption;
import LLD.AtmMachine.model.Card;
import LLD.AtmMachine.service.AtmMachine;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public abstract class AtmState {
    protected final AtmMachine atmMachine;

    public void insertCard(Card card) {
        throw new RuntimeException("Invalid Operation !!! ");
    }

    public void enterPin(String pin) {
        throw new RuntimeException("Invalid Operation !!! ");
    }

    public void selectOption(AtmOption option) {
        throw new RuntimeException("Invalid Operation !!! ");
    }

    public void dispenseCash(double amount) {
        throw new RuntimeException("Invalid Operation !!! ");
    }

    public void addAmount(Map<Integer, Integer> cash) {
        throw new RuntimeException("Invalid Operation !!! ");
    }

    protected void setNextState(AtmState nextState) {
        atmMachine.setState(nextState);
        atmMachine.getAtm().setAtmStatus(nextState.atmMachine.getStatus());
    }
}
