package LLD.ParkingLot.strategy;

import LLD.ParkingLot.models.Ticket;

public interface PaymentStrategy {
    boolean processPayment(Ticket ticket, double amount);
}
