package LLD.ParkingLot.strategy.impl;

import LLD.ParkingLot.strategy.FeeStrategy;
import LLD.ParkingLot.models.SpotSize;
import LLD.ParkingLot.vehicles.VehicleType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

public class EventBasedFeeStrategy implements FeeStrategy {

    private final Map<VehicleType, Double> eventFees;

    public EventBasedFeeStrategy() {
        // default values
        this.eventFees = Map.of(
                VehicleType.MOTORCYCLE, 10.0,
                VehicleType.CAR, 20.0,
                VehicleType.TRUCK, 30.0,
                VehicleType.BUS, 40.0
        );
    }

    public void setFee(VehicleType vehicleType, double fee) {
        eventFees.put(vehicleType, fee);
    }

    @Override
    public double calculateFee(SpotSize spotSize, VehicleType vehicleType, LocalDateTime entryTime, LocalDateTime exitTime) {
        if (exitTime.isBefore(entryTime)) throw new IllegalArgumentException("Exit time before entry time");
        int hours = (int) Math.ceil(Duration.between(entryTime, exitTime).toMinutes() / 60.0);
        return hours * eventFees.get(vehicleType);
    }
}
