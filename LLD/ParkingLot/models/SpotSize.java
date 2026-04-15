package LLD.ParkingLot.models;

import lombok.Getter;

@Getter
public enum SpotSize {
    SMALL(1),
    MEDIUM(2),
    LARGE(3),
    EXTRA_LARGE(4);

    private final int comparisonValue;

    SpotSize(int comparisonValue) {
        this.comparisonValue = comparisonValue;
    }
}

