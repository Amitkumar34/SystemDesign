package LLD.AtmMachine.model;

import LLD.AtmMachine.enums.AtmStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

@Getter
@Setter
public class Atm {
    private final String id;
    private AtmStatus atmStatus;
    private double availableCash;
    private final Map<Integer, Integer> denominationCount;

    public Atm(String id) {
        this.id = id;
        this.atmStatus = AtmStatus.CASH_FILLING_STATE;
        this.availableCash = 0;
        this.denominationCount = new TreeMap<>(Comparator.reverseOrder());
    }

    public void addAmount(Map<Integer, Integer> cash) {
        if (cash == null) return;
        cash.entrySet().forEach(e -> {
            int denomination = e.getKey();
            int count = e.getValue();
            denominationCount.compute(denomination, (k, currentCount)
                    -> (currentCount == null ? 0 : currentCount) + count);
            availableCash += denomination * count;
        });
    }
}
