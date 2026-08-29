package LLD.PizzaDecorator.BasePizza;

import LLD.PizzaDecorator.Pizza;

public class Magherita extends Pizza {
    @Override
    public int getCost() {
        return 100;
    }

    @Override
    public String getDesc() {
        return "Magherita Pizza";
    }
}
