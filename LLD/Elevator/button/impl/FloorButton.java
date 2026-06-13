package LLD.Elevator.button.impl;

import LLD.Elevator.button.Button;
import LLD.Elevator.cart.Cart;
import LLD.Elevator.dispatcher.CartDispatcher;
import LLD.Elevator.models.Direction;
import LLD.Elevator.models.Floor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
public class FloorButton implements Button {
    @Getter
    private final Direction direction;
    private final CartDispatcher cartDispatcher;
    private final Floor floor;

    @Override
    public void press() {
        cartDispatcher.assignCart(floor, direction);
    }
}
