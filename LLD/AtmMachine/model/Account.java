package LLD.AtmMachine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Account {
    private final String accountNumber;
    private double balance;
}
