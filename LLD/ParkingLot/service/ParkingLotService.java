package LLD.ParkingLot.service;

import LLD.ParkingLot.mapper.VehicleToSpotSizeMapper;
import LLD.ParkingLot.mapper.impl.DefaultVehicleToSpotSizeMapper;
import LLD.ParkingLot.models.ParkingSpot;
import LLD.ParkingLot.models.SpotSize;
import LLD.ParkingLot.models.Ticket;
import LLD.ParkingLot.store.SpotStore;
import LLD.ParkingLot.vehicles.Vehicle;

import java.util.List;

public class ParkingLotService {
    private static ParkingLotService instance;

    private SpotStore spotStore;
    private VehicleToSpotSizeMapper vehicleToSpotSizeMapper;

    private ParkingLotService() {
        this.vehicleToSpotSizeMapper = new DefaultVehicleToSpotSizeMapper();
    }

    public static ParkingLotService getInstance() {
        if (instance == null) {
            instance = new ParkingLotService();
        }
        return instance;
    }

    public void setSpotStore(SpotStore spotStore) {
        this.spotStore = spotStore;
    }

    public SpotStore getSpotStore() {
        return spotStore;
    }

    public void setVehicleToSpotSizeMapper(VehicleToSpotSizeMapper mapper) {
        this.vehicleToSpotSizeMapper = mapper;
    }

    public boolean addSpot(ParkingSpot spot) {
        if (spotStore == null) throw new RuntimeException("No SpotStore available");
        return spotStore.addSpot(spot);
    }

    public boolean removeSpot(ParkingSpot spot) {
        if (spotStore == null) throw new RuntimeException("No SpotStore available");
        return spotStore.removeSpot(spot);
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        List<SpotSize> compatibleSizes = vehicleToSpotSizeMapper.getCompatibleSpotSizes(vehicle.getVehicleType());
        ParkingSpot availableSpot = spotStore.getAnySpotAvailable(compatibleSizes.toArray(new SpotSize[0]));

        if (availableSpot == null) {
            throw new RuntimeException("No spot available for " + vehicle);
        }

        if (availableSpot.parkVehicle(vehicle)) {
            return new Ticket(availableSpot);
        }
        return null;
    }

    public Vehicle unPark(Ticket ticket) {
        if (ticket == null) throw new RuntimeException("Invalid Ticket");
        ParkingSpot spot = ticket.getParkingSpot();
        if (spot == null) throw new RuntimeException("Invalid Spot");
        return spot.unParkVehicle(true);
    }
}

