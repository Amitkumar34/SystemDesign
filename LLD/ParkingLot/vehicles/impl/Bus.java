package LLD.ParkingLot.vehicles.impl;

import LLD.ParkingLot.vehicles.Vehicle;
import LLD.ParkingLot.vehicles.VehicleType;

public class Bus extends Vehicle {

    public Bus(String licensePlate) {
        super(VehicleType.BUS, licensePlate);
    }

    @Override
    public String toString() {
        return "Bus[" + getLicensePlate() + "]";
    }
}
