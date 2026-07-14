package LLD.PizzaDecorator.BasePizza;

import LLD.PizzaDecorator.Pizza;

public class VegDelight extends Pizza {
    @Override
    public int getCost() {
        return 200;
    }

    @Override
    public String getDesc() {
        return "VegDelight Pizza";
    }
}
