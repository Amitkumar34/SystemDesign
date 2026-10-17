package LLD.patterns.PizzaDecorator.Toppings.impl;

import LLD.patterns.PizzaDecorator.Pizza;
import LLD.patterns.PizzaDecorator.Toppings.Toppings;

public class Mushroom extends Toppings {
    public Mushroom(Pizza basePizza) {
        super(basePizza);
    }

    @Override
    public int getCost() {
        return basePizza.getCost() + 20;
    }

    @Override
    public String getDesc() {
        return basePizza.getDesc() + " with Mushroom";
    }
}
