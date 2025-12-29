package LLD.ParkingLot.vehicles.impl;

import LLD.ParkingLot.vehicles.Vehicle;
import LLD.ParkingLot.vehicles.VehicleType;

public class Motorcycle extends Vehicle {

    public Motorcycle(String licensePlate) {
        super(VehicleType.MOTORCYCLE, licensePlate);
    }

    @Override
    public String toString() {
        return "Motorcycle[" + getLicensePlate() + "]";
    }
}
