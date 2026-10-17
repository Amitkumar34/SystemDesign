package LLD.Elevator.dispatcher.impl;

import LLD.Elevator.cart.Cart;
import LLD.Elevator.dispatcher.CartDispatcher;
import LLD.Elevator.models.Direction;
import LLD.Elevator.models.Floor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NearestCartDispatcher implements CartDispatcher {

    private static final int IDLE_POLL_MS = 100;
    private static final int FLOOR_MOVE_MS = 500;

    private final ExecutorService executorService;

    public NearestCartDispatcher(int noOfThreads) {
        this.executorService = Executors.newFixedThreadPool(noOfThreads);
    }

    @Override
    public Cart assignCart(Floor floor, Direction direction) {
        int floorIndex = floor.getFloorIndex();
        Cart bestCart = null;
        int bestTime = Integer.MAX_VALUE;

        for (Cart cart : floor.getCarts()) {
            int time = cart.calcTimetoReach(floorIndex, direction);
            if (time < bestTime) {
                bestTime = time;
                bestCart = cart;
            }
        }

        if (bestCart == null) {
            throw new IllegalStateException("No carts available on floor " + floorIndex);
        }
        bestCart.addStoppage(floorIndex);
        return bestCart;
    }

    @Override
    public void runAllCarts(List<Cart> carts) {
        for (Cart cart : carts) {
            executorService.submit(() -> runCart(cart));
        }
    }

    private void runCart(Cart cart) {
        try {
            while (true) {
                if (cart.getMovingDirection() == Direction.IDLE) {
                    Thread.sleep(IDLE_POLL_MS);
                    continue;
                }

                switch (cart.getMovingDirection()) {
                    case UP -> {
                        if (cart.getCurrentFloorIndex() == cart.getStoppages().length - 1) {
                            cart.setMovingDirection(Direction.DOWN);
                        } else {
                            cart.setCurrentFloorIndex(cart.getCurrentFloorIndex() + 1);
                            cart.stopIfStoppage();
                            Thread.sleep(FLOOR_MOVE_MS);
                        }
                    }
                    case DOWN -> {
                        if (cart.getCurrentFloorIndex() == 0) {
                            cart.setMovingDirection(Direction.UP);
                        } else {
                            cart.setCurrentFloorIndex(cart.getCurrentFloorIndex() - 1);
                            cart.stopIfStoppage();
                            Thread.sleep(FLOOR_MOVE_MS);
                        }
                    }
                    default -> Thread.sleep(IDLE_POLL_MS);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
