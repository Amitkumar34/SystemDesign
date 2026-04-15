package LLD.ParkingLot.mapper.impl;

import LLD.ParkingLot.mapper.VehicleToSpotSizeMapper;
import LLD.ParkingLot.models.SpotSize;
import LLD.ParkingLot.vehicles.VehicleType;

import java.util.List;

public class DefaultVehicleToSpotSizeMapper implements VehicleToSpotSizeMapper {
    @Override
    public List<SpotSize> getCompatibleSpotSizes(VehicleType vehicleType) {
        return switch (vehicleType) {
            case MOTORCYCLE -> List.of(SpotSize.SMALL, SpotSize.MEDIUM);
            case CAR -> List.of(SpotSize.MEDIUM);
            case TRUCK -> List.of(SpotSize.LARGE);
            case BUS -> List.of(SpotSize.EXTRA_LARGE);
        };
    }
}
