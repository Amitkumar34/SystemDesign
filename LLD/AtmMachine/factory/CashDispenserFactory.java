package LLD.AtmMachine.factory;

import LLD.AtmMachine.CashDispenser;
import LLD.AtmMachine.model.Atm;

import java.util.Map;

public class CashDispenserFactory {
    private CashDispenserFactory() {
    }

    public static CashDispenser create(Atm atm) {
        CashDispenser cashDispenser = null;
        CashDispenser curr = null;
        for (Map.Entry<Integer, Integer> e : atm.getDenominationCount().entrySet()) {
            if (curr == null) cashDispenser = curr = new CashDispenser(e.getKey());
            else {
                curr.setNextCashDispenser(new CashDispenser(e.getKey()));
                curr = curr.getNextCashDispenser();
            }
        }
        return cashDispenser;
    }
}
