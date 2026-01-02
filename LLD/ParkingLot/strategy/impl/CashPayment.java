package LLD.ParkingLot.strategy.impl;

import LLD.ParkingLot.strategy.PaymentStrategy;
import LLD.ParkingLot.models.Ticket;

public class CashPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(Ticket ticket, double amount) {
        System.out.println("Processing fee for ticket: " + ticket.getTicketId() + " amount: " + amount + " using Cash");
        return true;
    }
}