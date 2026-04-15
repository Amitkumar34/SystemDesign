package LLD.ParkingLot.vehicles;

import LLD.ParkingLot.vehicles.impl.Bus;
import LLD.ParkingLot.vehicles.impl.Car;
import LLD.ParkingLot.vehicles.impl.Motorcycle;
import LLD.ParkingLot.vehicles.impl.Truck;

public class VehicleFactory {

    public static Vehicle createVehicle(VehicleType vehicleType, String licensePlate) {
        return switch (vehicleType) {
            case CAR -> new Car(licensePlate);
            case MOTORCYCLE -> new Motorcycle(licensePlate);
            case TRUCK -> new Truck(licensePlate);
            case BUS -> new Bus(licensePlate);
        };
    }
}
