package LLD.ParkingLot.vehicles;

import lombok.Getter;

@Getter
public abstract class Vehicle {
    protected final VehicleType vehicleType;
    protected final String licensePlate;

    protected Vehicle(VehicleType vehicleType, String licensePlate) {
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
    }
}
