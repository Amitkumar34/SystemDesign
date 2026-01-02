package LLD.ParkingLot.models;

import LLD.ParkingLot.vehicles.Vehicle;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicBoolean;


public class ParkingSpot {
    private static int NEXT_SPOT_ID = 0;
    @Getter
    private final int spotId;
    @Getter
    private SpotSize spotSize;

    private Vehicle vehicle;
    private AtomicBoolean isOccupied;

    public ParkingSpot(SpotSize spotSize) {
        this.spotId = NEXT_SPOT_ID++;
        this.spotSize = spotSize;
        this.isOccupied = new AtomicBoolean(false);
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if (this.vehicle != null || !this.isOccupied.compareAndSet(false, true)) {
            throw new RuntimeException("Lot is already occupied");
        }
        this.vehicle = vehicle;
        return true;
    }

    public Vehicle getVehicle() {
        if (vehicle == null || !this.isOccupied.get()) throw new RuntimeException("Vehicle is not Present");
        return vehicle;
    }

    public Vehicle unParkVehicle(boolean unPark) {
        Vehicle parkedVehicle = getVehicle();
        if (unPark) {
            if (this.isOccupied.compareAndSet(true, false)) {
                this.vehicle = null;
            } else {
                throw new RuntimeException("Lot is not occupied");
            }
        }
        return parkedVehicle;
    }

    public boolean isOccupied() {
        return isOccupied.get();
    }
}

