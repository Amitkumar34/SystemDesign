package LLD.PizzaDecorator;

import LLD.PizzaDecorator.BasePizza.VegDelight;
import LLD.PizzaDecorator.Toppings.impl.ExtraCheese;
import LLD.PizzaDecorator.Toppings.impl.Mushroom;

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
