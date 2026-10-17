package LLD.patterns.PizzaDecorator.Toppings;

import LLD.patterns.PizzaDecorator.Pizza;

public abstract class Toppings extends Pizza {
    protected Pizza basePizza;

    protected Toppings(Pizza basePizza) {
        this.basePizza = basePizza;
    }
}
