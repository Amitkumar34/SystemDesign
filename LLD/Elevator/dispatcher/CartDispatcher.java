package LLD.Elevator.dispatcher;

import LLD.Elevator.cart.Cart;
import LLD.Elevator.models.Direction;
import LLD.Elevator.models.Floor;

import java.util.List;

public interface CartDispatcher {
    Cart assignCart(Floor floor, Direction direction);
    void runAllCarts(List<Cart> carts);
}
