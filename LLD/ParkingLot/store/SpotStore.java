package LLD.ParkingLot.store;

import LLD.ParkingLot.models.ParkingSpot;
import LLD.ParkingLot.models.SpotSize;
import LLD.ParkingLot.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpotStore {

    private final Map<SpotSize, List<ParkingSpot>> availableSpots;
    private final Map<SpotSize, List<ParkingSpot>> occupiedSpots;

    public SpotStore() {
        availableSpots = new HashMap<>();
        occupiedSpots = new HashMap<>();
    }

    public ParkingSpot getAnySpotAvailable(SpotSize... spotSizes) {
        for (SpotSize size : spotSizes) {
            List<ParkingSpot> spots = availableSpots.get(size);
            if (spots != null && !spots.isEmpty()) {
                for (ParkingSpot spot : spots) {
                    if (!spot.isOccupied()) return spot;
                }
            }
        }
        return null;
    }

    public boolean occupySpot(ParkingSpot spot, Vehicle vehicle) {
        return spot.parkVehicle(vehicle);
    }

    public boolean addSpot(ParkingSpot spot) {
        if (spot == null) throw new RuntimeException("Null spot cannot be added");
        List<ParkingSpot> spots = availableSpots.get(spot.getSpotSize());
        if (spots == null) {
            spots = new ArrayList<>();
            availableSpots.put(spot.getSpotSize(), spots);
        }
        return spots.add(spot);
    }

    public boolean removeSpot(ParkingSpot spot) {
        if (spot == null) throw new RuntimeException("Null spot cannot be removed");
        List<ParkingSpot> occupied = occupiedSpots.get(spot.getSpotSize());
        if (occupied != null && occupied.contains(spot)) {
            throw new RuntimeException("Spot is occupied, cannot remove. Status: " + spot.isOccupied());
        }
        List<ParkingSpot> available = availableSpots.get(spot.getSpotSize());
        if (available == null || !available.contains(spot)) {
            throw new RuntimeException("Spot not found");
        }
        return available.remove(spot);
    }
}

