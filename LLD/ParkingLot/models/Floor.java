package LLD.ParkingLot.models;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Floor {
    private static int NEXT_FLOOR_ID = 0;

    @Getter
    private final int floorId;
    private Map<Integer, ParkingSpot> parkingSpots;

    public Floor() {
        this.floorId = NEXT_FLOOR_ID++;
        this.parkingSpots = new HashMap<>();
    }

    public void addSpot(ParkingSpot spot) {
        this.parkingSpots.put(spot.getSpotId(), spot);
    }

    public void removeSpot(ParkingSpot spot) {
        this.parkingSpots.remove(spot.getSpotId());
    }

    public ParkingSpot getSpot(int spotId) {
        return this.parkingSpots.get(spotId);
    }

    public ParkingSpot getAnySpotAvailable(SpotSize spotSize) {
        for (ParkingSpot spot : this.parkingSpots.values()) {
            if (spot.getSpotSize() == spotSize && !spot.isOccupied()) {
                return spot;
            }
        }
        return null;
    }
}
