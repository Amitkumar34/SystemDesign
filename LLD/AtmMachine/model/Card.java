package LLD.AtmMachine.model;

import lombok.Data;

@Data
public class Card {
    private final String cardNumber;
    private final String pin;
    private final Account account;
}
