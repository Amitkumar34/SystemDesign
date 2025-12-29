package LLD.ParkingLot.vehicles;

public abstract class Vehicle {
    protected final VehicleType vehicleType;
    protected final String licensePlate;

    protected Vehicle(VehicleType vehicleType, String licensePlate) {
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
