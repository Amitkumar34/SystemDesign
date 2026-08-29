package LLD.PizzaDecorator.Toppings;

import LLD.PizzaDecorator.Pizza;

public abstract class Toppings extends Pizza {
    protected Pizza basePizza;

    protected Toppings(Pizza basePizza) {
        this.basePizza = basePizza;
    }
}
