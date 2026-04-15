package LLD.ParkingLot.vehicles.impl;

import LLD.ParkingLot.vehicles.Vehicle;
import LLD.ParkingLot.vehicles.VehicleType;

public class Car extends Vehicle {

    public Car(String licensePlate) {
        super(VehicleType.CAR, licensePlate);
    }

    @Override
    public String toString() {
        return "Car[" + getLicensePlate() + "]";
    }
}
