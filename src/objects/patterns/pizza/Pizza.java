package objects.patterns.pizza;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by ksenia on 20.04.2017.
 */
public class Pizza {
    private final PizzasName name;
    private final PizzasDough dough;
    private final PizzasCheese cheese;
    private final PizzasSause sauce;
    private final Collection<PizzasComponents> components;

    public static class PizzaBuilder {

        private final PizzasName name;
        private PizzasCheese cheese;
        private PizzasDough dough;
        private PizzasSause sauce;
        private Collection<PizzasComponents> components;

        public PizzaBuilder(PizzasName name) {
            this.name = name;
            components = new HashSet<>();

            switch (name) {
                case MARGARITA:
                    dough = PizzasDough.SOLID;
                    cheese = PizzasCheese.MOZARELLA;
                    sauce = PizzasSause.TOMATO;
                    break;
                case HAWAIIAN:
                    dough = PizzasDough.LAYERED;
                    cheese = PizzasCheese.CHEDDER;
                    sauce = PizzasSause.GARLIC;
                    components.add(PizzasComponents.CHICKEN);
                    components.add(PizzasComponents.PINEAPPLE);
                    components.add(PizzasComponents.SHRIMP);
                    break;
                default:
                    dough = PizzasDough.SOLID;
                    cheese = PizzasCheese.GAUDA;
                    sauce = PizzasSause.TOMATO;
                    break;
            }
        }

        public PizzaBuilder dough(PizzasDough dough) {
            this.dough = dough;
            return this;
        }

        public PizzaBuilder cheese(PizzasCheese cheese) {
            this.cheese = cheese;
            return this;
        }

        public PizzaBuilder sauce(PizzasSause sauce) {
            this.sauce = sauce;
            return this;
        }

        public PizzaBuilder addComponent(PizzasComponents component) {
            components.add(component);
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }

    private Pizza(PizzaBuilder pizzaBuilder) {
        name = pizzaBuilder.name;
        dough = pizzaBuilder.dough;
        cheese = pizzaBuilder.cheese;
        sauce = pizzaBuilder.sauce;
        components = pizzaBuilder.components;
    }

    @Override
    public String toString() {
        return "Your pizza: '" + name.toString() + "':\n" +
                "dough: " + dough + "\n" +
                "cheese: " + cheese + "\n" +
                "sauce: " + sauce + "\n" +
                "components: " + components + "\n";
    }

    public enum PizzasName {
        MARGARITA("Margarita"), HAWAIIAN("Hawaiian"), CUSTOM("Custom");

        private String description;

        PizzasName(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }
    }

    public enum PizzasDough {SOLID, LAYERED}

    public enum PizzasCheese {MOZARELLA, CHEDDER, GAUDA, MAASDAM}

    public enum PizzasSause {TOMATO, GARLIC}

    public enum PizzasComponents {TOMATO, CHICKEN, PINEAPPLE, SHRIMP, OLIVES, MUSHROOMS, CUCUMBER}
}
