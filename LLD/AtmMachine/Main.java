package LLD.AtmMachine;

import LLD.AtmMachine.enums.AtmOption;
import LLD.AtmMachine.model.Account;
import LLD.AtmMachine.model.Atm;
import LLD.AtmMachine.model.Card;
import LLD.AtmMachine.repo.AtmRepo;
import LLD.AtmMachine.service.AtmMachine;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Atm atm = new Atm("ATM1");
        AtmRepo atmRepo = new AtmRepo();
        atmRepo.save(atm);
        AtmMachine atmMachine = new AtmMachine(atmRepo, atm.getId());
        atmMachine.addAmount(Map.of(
                1000, 5,
                500, 3,
                200, 3,
                100, 1
        ));

        Card card = new Card("12345", "123", new Account("12345", 5000));

        atmMachine.insertCard(card);
        atmMachine.enterPin("123");
        atmMachine.selectOption(AtmOption.WITHDRAW);
        atmMachine.dispenseCash(600);
        atmMachine.dispenseCash(600);
        atmMachine.ejectCard();
    }
}
