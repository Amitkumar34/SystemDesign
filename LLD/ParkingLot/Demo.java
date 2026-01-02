package LLD.ParkingLot;

import LLD.ParkingLot.models.*;
import LLD.ParkingLot.service.ParkingLotService;
import LLD.ParkingLot.store.SpotStore;
import LLD.ParkingLot.vehicles.Vehicle;
import LLD.ParkingLot.vehicles.VehicleFactory;
import LLD.ParkingLot.vehicles.VehicleType;
import LLD.ParkingLot.vehicles.impl.Bus;

public class Demo {
    public static void main(String[] args) {
        System.out.println("=== Parking Lot Demo ===\n");

        // 1. Initialize ParkingLotService (Singleton)
        ParkingLotService parkingService = ParkingLotService.getInstance();

        // 2. Create and set the SpotStore
        SpotStore spotStore = new SpotStore();
        parkingService.setSpotStore(spotStore);

        // 3. Add parking spots of various sizes
        System.out.println("--- Adding Parking Spots ---");
        parkingService.addFloor(new Floor());
        // Add 3 small spots (for motorcycles)
        for (int i = 1; i <= 3; i++) {
            parkingService.addSpot(0, new ParkingSpot(SpotSize.SMALL));
            System.out.println("Added SMALL spot #" + i);
        }

        // Add 5 medium spots (for cars)
        for (int i = 1; i <= 5; i++) {
            parkingService.addSpot(0, new ParkingSpot(SpotSize.MEDIUM));
            System.out.println("Added MEDIUM spot #" + i);
        }

        // Add 2 large spots (for trucks)
        for (int i = 1; i <= 2; i++) {
            parkingService.addSpot(0, new ParkingSpot(SpotSize.LARGE));
            System.out.println("Added LARGE spot #" + i);
        }

        // Add 1 extra large spot (for buses)
        parkingService.addSpot(0, new ParkingSpot(SpotSize.EXTRA_LARGE));
        System.out.println("Added EXTRA_LARGE spot #1");

        System.out.println("\nTotal spots: 3 SMALL, 5 MEDIUM, 2 LARGE, 1 EXTRA_LARGE\n");

        // 4. Create some vehicles
        System.out.println("--- Creating Vehicles ---");
        Vehicle bike1 = VehicleFactory.createVehicle(VehicleType.MOTORCYCLE, "BIKE-001");
        Vehicle bike2 = VehicleFactory.createVehicle(VehicleType.MOTORCYCLE, "BIKE-002");
        Vehicle car1 = VehicleFactory.createVehicle(VehicleType.CAR, "CAR-1234");
        Vehicle car2 = VehicleFactory.createVehicle(VehicleType.CAR, "CAR-5678");
        Vehicle car3 = VehicleFactory.createVehicle(VehicleType.CAR, "CAR-9999");
        Vehicle truck1 = VehicleFactory.createVehicle(VehicleType.TRUCK, "TRUCK-100");
        Vehicle bus1 = VehicleFactory.createVehicle(VehicleType.BUS, "BUS-500");

        System.out.println("Created: " + bike1 + ", " + bike2);
        System.out.println("Created: " + car1 + ", " + car2 + ", " + car3);
        System.out.println("Created: " + truck1);
        System.out.println("Created: " + bus1);
        System.out.println();

        // 5. Park vehicles and get tickets
        System.out.println("--- Parking Vehicles ---");

        Integer ticketId1 = parkingService.parkVehicle(bike1);
        System.out.println("Parked " + bike1 + " -> Ticket #" + ticketId1);

        Integer ticketId2 = parkingService.parkVehicle(car1);
        System.out.println("Parked " + car1 + " -> Ticket #" + ticketId2);

        Integer ticketId3 = parkingService.parkVehicle(car2);
        System.out.println("Parked " + car2 + " -> Ticket #" + ticketId3);

        Integer ticketId4 = parkingService.parkVehicle(truck1);
        System.out.println("Parked " + truck1 + " -> Ticket #" + ticketId4);

        Integer ticketId5 = parkingService.parkVehicle(bus1);
        System.out.println("Parked " + bus1 + " -> Ticket #" + ticketId5);

        System.out.println();

        // 6. Unpark a vehicle
        System.out.println("--- Unparking Vehicles ---");
        Vehicle unparkedVehicle = parkingService.unPark(ticketId2, PaymentMode.CASH);
        System.out.println("Unparked: " + unparkedVehicle + " (Ticket #" + ticketId2 + ")");

        // 7. Park another car in the freed spot
        Integer ticketId6 = parkingService.parkVehicle(car3);
        System.out.println("Parked " + car3 + " -> Ticket #" + ticketId6);

        System.out.println();

        // 8. Demonstrate spot full scenario
        System.out.println("--- Testing Spot Full Scenario ---");
        Bus bus2 = new Bus("BUS-600");
        try {
            parkingService.parkVehicle(bus2);
        } catch (RuntimeException e) {
            System.out.println("Failed to park " + bus2 + ": " + e.getMessage());
        }

        // Unpark the first bus to free the slot
        parkingService.unPark(ticketId5, PaymentMode.CARD);
        System.out.println("Unparked: " + bus1);

        // Now park bus2
        Integer ticketId7 = parkingService.parkVehicle(bus2);
        System.out.println("Parked " + bus2 + " -> Ticket #" + ticketId7);

        System.out.println();
        System.out.println("=== Demo Complete ===");
    }
}
