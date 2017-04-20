package objects.patterns.pizza;

/**
 * Created by ksenia on 20.04.2017.
 */
public class PizzaTest {
    public static void main(String[] args) {

        Pizza pizza1 = new Pizza.PizzaBuilder(Pizza.PizzasName.MARGARITA).build();
        Pizza pizza2 = new Pizza.PizzaBuilder(Pizza.PizzasName.CUSTOM)
                .cheese(Pizza.PizzasCheese.MAASDAM)
                .sauce(Pizza.PizzasSause.GARLIC)
                .addComponent(Pizza.PizzasComponents.CUCUMBER)
                .build();
        System.out.println(pizza1.toString());
        System.out.println(pizza2.toString());
    }
}
