package LLD.ParkingLot.mapper;

import LLD.ParkingLot.models.SpotSize;
import LLD.ParkingLot.vehicles.VehicleType;

import java.util.List;

public interface VehicleToSpotSizeMapper {
    List<SpotSize> getCompatibleSpotSizes(VehicleType vehicleType);
}
