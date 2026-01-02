package LLD.ParkingLot.store;

import LLD.ParkingLot.models.Floor;
import LLD.ParkingLot.models.ParkingSpot;
import LLD.ParkingLot.models.SpotSize;
import LLD.ParkingLot.vehicles.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class SpotStore {

    private final Map<Integer, Floor> floors;

    public SpotStore() {
        this.floors = new HashMap<>();
    }

    public ParkingSpot getAnySpotAvailable(SpotSize spotSize) {
        for (Floor floor : floors.values()) {
            ParkingSpot spot = floor.getAnySpotAvailable(spotSize);
            if (spot != null) return spot;
        }
        return null;
    }

    public boolean occupySpot(ParkingSpot spot, Vehicle vehicle) {
        if (spot == null) throw new RuntimeException("Null spot cannot be occupied");
        return spot.parkVehicle(vehicle);
    }

    public boolean addFloor(Floor floor) {
        if (floor == null) throw new RuntimeException("Null floor cannot be added");
        floors.put(floor.getFloorId(), floor);
        return true;
    }

    public Floor getFloor(Integer floorId) {
        return floors.get(floorId);
    }

    public boolean removeFloor(Integer floorId) {
        Floor floor = floors.get(floorId);
        if (floor == null) throw new RuntimeException("Floor not found");
        floors.remove(floorId);
        return true;
    }

    public boolean addSpot(Integer floorId, ParkingSpot spot) {
        Floor floor = floors.get(floorId);
        if (floor == null) throw new RuntimeException("Floor not found");
        if (spot == null) throw new RuntimeException("Null spot cannot be added");
        floor.addSpot(spot);
        return true;
    }

    public ParkingSpot getSpot(Integer spotId) {
        for (Floor floor : floors.values()) {
            ParkingSpot spot = floor.getSpot(spotId);
            if (spot != null) return spot;
        }
        return null;
    }

    public boolean removeSpot(Integer floorId, Integer spotId) {
        Floor floor = floors.get(floorId);
        if (floor == null) throw new RuntimeException("Floor not found");
        ParkingSpot spot = floor.getSpot(spotId);
        if (spot == null) throw new RuntimeException("Spot not found");
        floor.removeSpot(spot);
        return true;
    }
}

