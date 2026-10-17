package LLD.Elevator.button.impl;

import LLD.Elevator.button.Button;
import LLD.Elevator.cart.Cart;
import LLD.Elevator.models.Floor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class CartButton implements Button {
    @Getter
    @Setter
    private Cart cart;
    @Getter
    private final int destinationFloorIndex;


    @Override
    public void press() {
        if (cart != null)
            cart.addStoppage(destinationFloorIndex);
    }
}
