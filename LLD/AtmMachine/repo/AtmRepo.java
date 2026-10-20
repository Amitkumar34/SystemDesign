package LLD.AtmMachine.repo;

import LLD.AtmMachine.enums.AtmStatus;
import LLD.AtmMachine.model.Atm;

import java.util.HashMap;
import java.util.Map;

public class AtmRepo {
    private final Map<String, Atm> atms = new HashMap<>();

    public void save(Atm atm) {
        atms.put(atm.getId(), atm);
    }

    public Atm getById(String atmId) {
        return atms.get(atmId);
    }

    public void updateStatus(String atmId, AtmStatus status) {
        atms.get(atmId).setAtmStatus(status);
    }
}
