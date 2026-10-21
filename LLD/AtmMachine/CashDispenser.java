package LLD.AtmMachine;

import LLD.AtmMachine.model.Atm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@RequiredArgsConstructor
@Getter
@Setter
public class CashDispenser {
    private CashDispenser nextCashDispenser;
    private final int denomination;

    public Map<Integer, Integer> canDispense(Atm atm, double amount) {
        if (atm.getAvailableCash() < amount)
            return null;
        if (amount == 0) return new HashMap<>();

        Integer count = atm.getDenominationCount().get(denomination);
        if (count == null || count < 0) count = 0;
        count = Math.min((int) amount / denomination, count);
        while (count >= 0) {
            double reducedAmount = amount - count * denomination;
            if (reducedAmount == 0) return new HashMap<>(Map.of(denomination, count));
            if (nextCashDispenser != null) {
                Map<Integer, Integer> mp = nextCashDispenser.canDispense(atm, reducedAmount);
                if (mp != null) {
                    if (count > 0) mp.put(denomination, count);
                    return mp;
                }
            }
            count--;
        }
        return null;
    }


    public String dispense(Atm atm, Map<Integer, Integer> cashMap) {
        StringBuilder sb = new StringBuilder();

        if (cashMap.containsKey(denomination))
            atm.getDenominationCount().computeIfPresent(denomination, (key, count) -> {
                int requiredCount = cashMap.get(denomination);
                sb.append(requiredCount + " x ₹" + denomination + " + ");
                return count - requiredCount;
            });

        if (nextCashDispenser != null) sb.append(nextCashDispenser.dispense(atm, cashMap));
        return sb.toString();
    }
}
