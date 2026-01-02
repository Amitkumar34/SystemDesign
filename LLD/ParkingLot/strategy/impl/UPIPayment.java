package LLD.ParkingLot.strategy.impl;

import LLD.ParkingLot.strategy.PaymentStrategy;
import LLD.ParkingLot.models.Ticket;

public class UPIPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(Ticket ticket, double amount) {
        System.out.println("Processing fee for ticket: " + ticket.getTicketId() + " amount: " + amount + " using UPI");
        return true;
    }
}