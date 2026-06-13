package LLD.Elevator.cart.impl;

import LLD.Elevator.button.Button;
import LLD.Elevator.button.impl.CartButton;
import LLD.Elevator.cart.Cart;
import LLD.Elevator.models.Direction;

public class CartImpl extends Cart {
    int numberOfStoppages = 0;

    public CartImpl(int cartId, int currentFloorIndex) {
        super(cartId);
        this.currentFloorIndex = currentFloorIndex;
    }

    @Override
    public void addButton(Button button) {
        ((CartButton) button).setCart(this);
        buttons.add(button);
    }


    @Override
    public boolean addStoppage(int floorIndex) {
        if (servableFloors[floorIndex] && !stoppages[floorIndex]) {
            stoppages[floorIndex] = true;
            numberOfStoppages++;
            if (numberOfStoppages == 1) {
                movingDirection = getNearestStoppageDirection();
            }
        }
        return stoppages[floorIndex];
    }

    @Override
    public void addServableFloors(int... floorIndices) {
        for (int floorIndex : floorIndices) {
            servableFloors[floorIndex] = true;
        }
    }

    @Override
    public int calcTimetoReach(final int floorIndex, Direction requiredDirection) {
        if (floorIndex < 0 || servableFloors.length <= floorIndex || !servableFloors[floorIndex])
            return Integer.MAX_VALUE;
        if (movingDirection == Direction.IDLE)
            return Math.abs(floorIndex - currentFloorIndex);

        int time = 0;
        int curPos = currentFloorIndex;
        Direction curDirection = movingDirection;


        while (curPos != floorIndex || requiredDirection != curDirection) {
            time += stoppages[curPos] ? 3 : 1;
            if (curDirection == Direction.UP) {
                if (curPos == stoppages.length - 1) {
                    curDirection = Direction.DOWN;
                    curPos--;
                } else curPos++;
            } else {
                if (curPos == 0) {
                    curDirection = Direction.UP;
                    curPos++;
                } else curPos--;
            }
        }
        return time;
    }

    @Override
    public Direction getNearestStoppageDirection() {
        if (numberOfStoppages == 0 || (numberOfStoppages == 1 && stoppages[currentFloorIndex])) {
            return Direction.IDLE;
        }

        int upTime = Integer.MAX_VALUE;
        int downTime = Integer.MAX_VALUE;
        for (int i = currentFloorIndex + 1; i < stoppages.length; i++) {
            if (stoppages[i]) {
                upTime = i - currentFloorIndex;
                break;
            }
        }
        for (int i = currentFloorIndex - 1; i >= 0; i--) {
            if (stoppages[i]) {
                downTime = currentFloorIndex - i;
                break;
            }
        }
        return upTime <= downTime ? Direction.UP : Direction.DOWN;
    }

    @Override
    public void stopIfStoppage() {
        if (stoppages[currentFloorIndex]) {
            try {
                numberOfStoppages--;
                System.out.println("Cart#" + getCartId() + " stopped at floor " + currentFloorIndex);
                Thread.sleep(1000);
                stoppages[currentFloorIndex] = false;
                if (numberOfStoppages == 0) {
                    movingDirection = Direction.IDLE;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder activeStoppages = new StringBuilder("[");
        boolean first = true;
        for (int i = 0; i < stoppages.length; i++) {
            if (stoppages[i]) {
                if (!first) {
                    activeStoppages.append(", ");
                }
                activeStoppages.append(i);
                first = false;
            }
        }
        activeStoppages.append("]");
        return "Cart#" + getCartId() + "{floor=" + currentFloorIndex + ", direction=" + movingDirection + ", stoppages=" + activeStoppages + "}";
    }
}
