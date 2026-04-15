package LLD.ParkingLot.strategy;

import LLD.ParkingLot.models.SpotSize;
import LLD.ParkingLot.vehicles.VehicleType;

import java.time.LocalDateTime;

public interface FeeStrategy {
    double calculateFee(SpotSize spotSize, VehicleType vehicleType, LocalDateTime entryTime, LocalDateTime exitTime);
}