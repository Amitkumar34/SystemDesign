package LLD.ParkingLot.service;

import LLD.ParkingLot.models.PaymentStatus;
import LLD.ParkingLot.models.Ticket;
import LLD.ParkingLot.strategy.PaymentStrategy;

public class PaymentProcessor {
    private PaymentStrategy paymentStrategy;

    public PaymentProcessor(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public boolean processPayment(Ticket ticket, double amount) {
        boolean success = paymentStrategy.processPayment(ticket, amount);
        if (success) {
            ticket.setPaymentStatus(PaymentStatus.SUCCESS);
        } else {
            ticket.setPaymentStatus(PaymentStatus.FAILED);
        }
        return success;
    }
}