package LLD.Elevator.models;

import LLD.Elevator.button.impl.FloorButton;
import LLD.Elevator.cart.Cart;
import LLD.Elevator.dispatcher.CartDispatcher;
import lombok.Getter;

import java.util.List;

public class Floor {
    @Getter
    private final int floorIndex;
    @Getter
    private final List<Cart> carts;
    @Getter
    private final FloorButton upButton;
    @Getter
    private final FloorButton downButton;

    public Floor(int floorIndex, List<Cart> carts, CartDispatcher cartDispatcher) {
        this.floorIndex = floorIndex;
        this.carts = carts;
        this.upButton = new FloorButton(Direction.UP, cartDispatcher,this);
        this.downButton = new FloorButton(Direction.DOWN, cartDispatcher,this);
    }
}
