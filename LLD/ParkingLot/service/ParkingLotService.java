package LLD.ParkingLot.service;

import LLD.ParkingLot.models.*;
import LLD.ParkingLot.strategy.FeeStrategy;
import LLD.ParkingLot.strategy.PaymentStrategyFactory;
import LLD.ParkingLot.strategy.impl.TimeBasedFeeStrategy;
import LLD.ParkingLot.mapper.VehicleToSpotSizeMapper;
import LLD.ParkingLot.mapper.impl.DefaultVehicleToSpotSizeMapper;
import LLD.ParkingLot.store.SpotStore;
import LLD.ParkingLot.vehicles.Vehicle;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
public class ParkingLotService {
    private static volatile ParkingLotService instance;
    private final FeeStrategy feeStrategy;

    private SpotStore spotStore;
    private VehicleToSpotSizeMapper vehicleToSpotSizeMapper;

    //TODO: Improvement - Ticket Store can be made
    private final Map<Integer, Ticket> activeTickets;

    private ParkingLotService() {
        this.vehicleToSpotSizeMapper = new DefaultVehicleToSpotSizeMapper();
        this.feeStrategy = new TimeBasedFeeStrategy(9, 5, 30, 15);
        this.activeTickets = new HashMap<>();
    }

    public static ParkingLotService getInstance() {
        if (instance == null) {
            synchronized (ParkingLotService.class) {
                if (instance == null) instance = new ParkingLotService();
            }
        }
        return instance;
    }

    public boolean addFloor(Floor floor) {
        if (spotStore == null) throw new RuntimeException("No SpotStore available");
        return spotStore.addFloor(floor);
    }

    public boolean removeFloor(Integer floorId) {
        if (spotStore == null) throw new RuntimeException("No SpotStore available");
        return spotStore.removeFloor(floorId);
    }

    public boolean addSpot(Integer floorId, ParkingSpot spot) {
        if (spotStore == null) throw new RuntimeException("No SpotStore available");
        return spotStore.addSpot(floorId, spot);
    }

    public boolean removeSpot(Integer floorId, Integer spotId) {
        if (spotStore == null) throw new RuntimeException("No SpotStore available");
        return spotStore.removeSpot(floorId, spotId);
    }

    public Integer parkVehicle(Vehicle vehicle) {
        List<SpotSize> compatibleSizes = vehicleToSpotSizeMapper.getCompatibleSpotSizes(vehicle.getVehicleType());
        List<ParkingSpot> availableSpots = new ArrayList<>();
        for (SpotSize size : compatibleSizes) {
            ParkingSpot availableSpot = spotStore.getAnySpotAvailable(size);
            if (availableSpot != null) availableSpots.add(availableSpot);
        }
        if (availableSpots.isEmpty()) throw new RuntimeException("No spot available for " + vehicle);
        availableSpots.sort(Comparator.comparingInt(e -> e.getSpotSize().getComparisonValue()));
        ParkingSpot spot = availableSpots.getFirst();
        spot.parkVehicle(vehicle);
        Ticket ticket = new Ticket();
        ticket.setParkingSpotID(spot.getSpotId());
        ticket.setPaymentStatus(PaymentStatus.PENDING);
        activeTickets.put(ticket.getTicketId(), ticket);
        return ticket.getTicketId();
    }

    public Vehicle unPark(Integer ticketId, PaymentMode paymentMode) {
        Ticket ticket = activeTickets.get(ticketId);
        if (ticket == null) throw new RuntimeException("Invalid Ticket");
        ParkingSpot spot = spotStore.getSpot(ticket.getParkingSpotID());
        if (spot == null) throw new RuntimeException("Invalid Spot");
        ticket.setExitTime(LocalDateTime.now());
        ticket.setFee(feeStrategy.calculateFee(
                spot.getSpotSize(),
                spot.getVehicle().getVehicleType(),
                ticket.getEntryTime(),
                ticket.getExitTime()));
        PaymentProcessor paymentProcessor = new PaymentProcessor(PaymentStrategyFactory.createPaymentStrategy(paymentMode));
        boolean success = paymentProcessor.processPayment(ticket, ticket.getFee());
        if (!success) throw new RuntimeException("Payment failed");
        activeTickets.remove(ticket.getTicketId());
        return spot.unParkVehicle(true);
    }
}

