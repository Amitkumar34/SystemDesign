package LLD.Elevator;

import LLD.Elevator.button.impl.CartButton;
import LLD.Elevator.cart.Cart;
import LLD.Elevator.cart.impl.CartImpl;
import LLD.Elevator.dispatcher.impl.NearestCartDispatcher;
import LLD.Elevator.models.Direction;
import LLD.Elevator.models.Floor;
import LLD.ParkingLot.vehicles.impl.Car;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    private static final int MIN_FLOOR = 0;
    private static final int MAX_FLOOR = 10;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Elevator Demo ===\n");

        NearestCartDispatcher dispatcher = new NearestCartDispatcher(2);

        Cart cart1 = new CartImpl(1, 0);
        Cart cart2 = new CartImpl(2, 5);

        List<Cart> carts = new ArrayList<>();
        carts.add(cart1);
        carts.add(cart2);

        int[] allFloors = range(MIN_FLOOR, MAX_FLOOR);
        cart1.addServableFloors(allFloors);
        cart2.addServableFloors(allFloors);

        for (int floorIndex = MIN_FLOOR; floorIndex <= MAX_FLOOR; floorIndex++) {
            cart1.addButton(new CartButton(floorIndex));
            cart2.addButton(new CartButton(floorIndex));
        }

        List<Floor> floors = new ArrayList<>();
        for (int floorIndex = MIN_FLOOR; floorIndex <= MAX_FLOOR; floorIndex++) {
            floors.add(new Floor(floorIndex, carts, dispatcher));
        }

        dispatcher.runAllCarts(carts);

        printState("Initial state", cart1, cart2);

        System.out.println("--- Floor 3 presses UP ---");
        floors.get(3).getUpButton().press();
        Thread.sleep(1500);
        printState("After floor 3 UP request", cart1, cart2);

        System.out.println("--- Passenger in Cart#1 presses floor 7 ---");
        ((CartButton) cart1.getButtons().get(7)).press();
        Thread.sleep(1500);
        printState("After Cart#1 requests floor 7", cart1, cart2);

        System.out.println("--- Floor 1 presses DOWN ---");
        floors.get(1).getDownButton().press();
        Thread.sleep(2000);
        printState("After floor 1 DOWN request", cart1, cart2);

        System.out.println("--- Dispatcher ETA (floor 4, UP) ---");
        System.out.println("Cart#1 ETA: " + cart1.calcTimetoReach(4, Direction.UP));
        System.out.println("Cart#2 ETA: " + cart2.calcTimetoReach(4, Direction.UP));

        Thread.sleep(8000);
        printState("Final state", cart1, cart2);

        System.out.println("=== Demo Complete ===");
    }

    private static int[] range(int min, int max) {
        int[] arr = new int[max - min + 1];
        for (int i = min; i <= max; i++) {
            arr[i - min] = i;
        }
        return arr;
    }

    private static void printState(String label, Cart cart1, Cart cart2) {
        System.out.println(label + ":");
        System.out.println(cart1);
        System.out.println(cart2);
        System.out.println();
    }
}
