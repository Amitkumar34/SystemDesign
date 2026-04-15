package LLD.ParkingLot.strategy.impl;

import LLD.ParkingLot.strategy.FeeStrategy;
import LLD.ParkingLot.models.SpotSize;
import LLD.ParkingLot.vehicles.VehicleType;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
public class TimeBasedFeeStrategy implements FeeStrategy {
    private int peakHourStart;
    private int peakHourEnd;
    private double peakHourlyRate;
    private double offPeakHourlyRate;


    public TimeBasedFeeStrategy(int peakHourStart, int peakHourEnd, double peakHourlyRate, double offPeakHourlyRate) {
        this.peakHourStart = peakHourStart;
        this.peakHourEnd = peakHourEnd;
        this.peakHourlyRate = peakHourlyRate;
        this.offPeakHourlyRate = offPeakHourlyRate;
    }

    @Override
    public double calculateFee(SpotSize spotSize, VehicleType vehicleType, LocalDateTime entryTime, LocalDateTime exitTime) {
        if (exitTime.isBefore(entryTime)) throw new IllegalArgumentException("Exit time before entry time");
        int hours = (int) Math.ceil(Duration.between(entryTime, exitTime).toMinutes() / 60.0);
        int peakHours = 0, offPeakHours = 0;
        for (int i = 0; i < hours; i++) {
            if (entryTime.plusHours(i).getHour() >= peakHourStart && entryTime.plusHours(i).getHour() <= peakHourEnd) {
                peakHours++;
            } else {
                offPeakHours++;
            }
        }
        return peakHours * peakHourlyRate + offPeakHours * offPeakHourlyRate;
    }
}