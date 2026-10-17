package LLD.patterns.PizzaDecorator.BasePizza;

import LLD.patterns.PizzaDecorator.Pizza;

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
