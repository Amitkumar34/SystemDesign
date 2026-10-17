package LLD.patterns.PizzaDecorator.BasePizza;

import LLD.patterns.PizzaDecorator.Pizza;

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
