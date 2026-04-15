package LLD.ParkingLot.vehicles.impl;

import LLD.ParkingLot.vehicles.Vehicle;
import LLD.ParkingLot.vehicles.VehicleType;

public class Truck extends Vehicle {

    public Truck(String licensePlate) {
        super(VehicleType.TRUCK, licensePlate);
    }

    @Override
    public String toString() {
        return "Truck[" + getLicensePlate() + "]";
    }
}
