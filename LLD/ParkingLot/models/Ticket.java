package LLD.ParkingLot.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Ticket {
    private static int NEXT_TICKET_ID = 0;

    @Setter(AccessLevel.NONE)
    private final int ticketId;
    private Integer parkingSpotID;
    private double fee;
    private LocalDateTime entryTime, exitTime;
    private PaymentStatus paymentStatus;

    public Ticket() {
        this.ticketId = NEXT_TICKET_ID++;
        this.entryTime = LocalDateTime.now();
    }
}

