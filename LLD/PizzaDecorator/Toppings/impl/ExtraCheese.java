package LLD.PizzaDecorator.Toppings.impl;

import LLD.PizzaDecorator.Pizza;
import LLD.PizzaDecorator.Toppings.Toppings;

public class ExtraCheese extends Toppings {
    public ExtraCheese(Pizza basePizza) {
        super(basePizza);
    }

    @Override
    public int getCost() {
        return basePizza.getCost() + 60;
    }

    @Override
    public String getDesc() {
        return basePizza.getDesc() + " with Extra Cheese";
    }
}
