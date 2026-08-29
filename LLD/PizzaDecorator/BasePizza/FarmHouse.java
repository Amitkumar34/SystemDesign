package LLD.PizzaDecorator.BasePizza;

import LLD.PizzaDecorator.Pizza;

public class FarmHouse extends Pizza {
    @Override
    public int getCost() {
        return 150;
    }

    @Override
    public String getDesc() {
        return "FarmHouse Pizza";
    }
}
