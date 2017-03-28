package objects.lazyAccumulator;

import objects.accumulator.Operation;
import objects.linkedList.Stack;

/**
 * Created by ksenia on 29.03.2017.
 */
public class LazyAccumulatorStack {
    Stack stack;
    int startValue;
    int elementCount;

    public LazyAccumulatorStack(int startValue, Stack stack) {
        this.startValue = startValue;
        this.stack = stack;
    }

    public void add(int value, Operation operation) {
        Item item = new Item(value, operation);
        stack.push(item);
        elementCount++;
    }

    public int calculate() {
        Object o;
        while ((o = stack.poll()) != null) {
            startValue = ((Item) o).operation.apply(startValue, ((Item) o).value);
        }
        return startValue;
    }

    private static class Item {
        int value;
        Operation operation;

        Item(int value, Operation operation) {
            this.value = value;
            this.operation = operation;
        }
    }
}
