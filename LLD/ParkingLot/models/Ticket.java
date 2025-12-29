package LLD.ParkingLot.models;

public class Ticket {
    private static int NEXT_TICKET_ID = 0;
    private final int ticketId;
    private final ParkingSpot parkingSpot;

    public Ticket(ParkingSpot parkingSpot) {
        this.ticketId = NEXT_TICKET_ID++;
        this.parkingSpot = parkingSpot;
    }

    public int getTicketId() {
        return ticketId;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
}

