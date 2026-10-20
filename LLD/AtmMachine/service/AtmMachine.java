package LLD.AtmMachine.service;

import LLD.AtmMachine.CashDispenser;
import LLD.AtmMachine.enums.AtmStatus;
import LLD.AtmMachine.enums.AtmOption;
import LLD.AtmMachine.factory.CashDispenserFactory;
import LLD.AtmMachine.model.Atm;
import LLD.AtmMachine.model.Card;
import LLD.AtmMachine.repo.AtmRepo;
import LLD.AtmMachine.state.AtmState;
import LLD.AtmMachine.state.impl.CashDispenseState;
import LLD.AtmMachine.state.impl.CashFillingState;
import LLD.AtmMachine.state.impl.IdleAtmState;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class AtmMachine {
    private final AtmRepo atmRepo;
    private final Atm atm;
    private CashDispenser cashDispenser;
    private AtmState state;

    private Card currentCard;

    public AtmMachine(AtmRepo atmRepo, String atmId) {
        this.atmRepo = atmRepo;
        this.atm = atmRepo.getById(atmId);
        this.state = new CashFillingState(this);
    }

    public void insertCard(Card card) {
        state.insertCard(card);
    }

    public void enterPin(String pin) {
        state.enterPin(pin);
    }

    public void selectOption(AtmOption option) {
        state.selectOption(option);
    }

    public void dispenseCash(double amount) {
        state.dispenseCash(amount);
    }

    public void ejectCard() {
        System.out.println("Card ejected !!!");
        reset();
    }

    public void addAmount(Map<Integer, Integer> cash) {
        state.addAmount(cash);
        //create new dispenser based on new total cash
        cashDispenser = CashDispenserFactory.create(atm);
    }

    public AtmStatus getStatus() {
        return atm.getAtmStatus();
    }

    public void reset() {
        this.state = new IdleAtmState(this);
    }
}
