package LLD.patterns.PizzaDecorator;

import LLD.patterns.PizzaDecorator.BasePizza.VegDelight;
import LLD.patterns.PizzaDecorator.Toppings.impl.ExtraCheese;
import LLD.patterns.PizzaDecorator.Toppings.impl.Mushroom;

public class Demo {
    public static void main(String[] args) {
        Pizza pizza = new VegDelight();
        System.out.println(pizza.getDesc() + "\nCost: " + pizza.getCost());


        pizza = new Mushroom(pizza);
        pizza = new ExtraCheese(pizza);
        System.out.println("\nToppings addition....");
        System.out.println(pizza.getDesc() + "\nCost: " + pizza.getCost());
    }
}
