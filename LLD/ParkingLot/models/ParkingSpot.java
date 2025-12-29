package LLD.ParkingLot.models;

import LLD.ParkingLot.vehicles.Vehicle;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;

public class ParkingSpot {
    private SpotSize spotSize;
    private Vehicle vehicle;
    private AtomicBoolean isOccupied;

    public ParkingSpot(SpotSize spotSize) {
        this.spotSize = spotSize;
        this.isOccupied = new AtomicBoolean(false);
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if (this.vehicle != null || this.isOccupied.get()) {
            throw new RuntimeException("Lot is already occupied");
        }
        this.isOccupied.set(true);
        this.vehicle = vehicle;
        return true;
    }

    public Vehicle getVehicle() {
        if (vehicle == null) throw new NoSuchElementException("Vehicle is not Present");
        return vehicle;
    }

    public Vehicle unParkVehicle(boolean unpark) {
        if (vehicle == null) throw new NoSuchElementException("Vehicle is not Present");
        Vehicle parkedVehicle = vehicle;
        if (unpark) {
            isOccupied.set(false);
            vehicle = null;
        }
        return parkedVehicle;
    }

    public SpotSize getSpotSize() {
        return spotSize;
    }

    public boolean isOccupied() {
        return isOccupied.get();
    }
}

