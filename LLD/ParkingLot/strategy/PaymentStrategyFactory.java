package LLD.ParkingLot.strategy;

import LLD.ParkingLot.models.PaymentMode;
import LLD.ParkingLot.strategy.impl.CardPayment;
import LLD.ParkingLot.strategy.impl.CashPayment;
import LLD.ParkingLot.strategy.impl.UPIPayment;

public class PaymentStrategyFactory {
    public static PaymentStrategy createPaymentStrategy(PaymentMode paymentMode) {
        return switch (paymentMode) {
            case CASH -> new CashPayment();
            case CARD -> new CardPayment();
            case UPI -> new UPIPayment();
        };
    }
}