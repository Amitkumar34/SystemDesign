package LLD.Elevator.cart;

import LLD.Elevator.button.Button;
import LLD.Elevator.models.Direction;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public abstract class Cart {
    private final int cartId;
    protected final boolean[] stoppages;
    protected final boolean[] servableFloors;
    protected Direction movingDirection;
    protected final List<Button> buttons;
    protected int currentFloorIndex;

    protected Cart(int cartId) {
        stoppages = new boolean[1000];
        servableFloors = new boolean[1000];
        Arrays.fill(stoppages, false);
        Arrays.fill(servableFloors, false);
        this.cartId = cartId;
        this.buttons = new ArrayList<>();
        this.movingDirection = Direction.IDLE;
    }

    public abstract boolean addStoppage(int floorIndex);

    public abstract void addButton(Button button);

    public abstract int calcTimetoReach(int floorIndex, Direction requiredDirection);

    public abstract void addServableFloors(int... floorIndices);

    public abstract Direction getNearestStoppageDirection();

    public abstract void stopIfStoppage();
}
