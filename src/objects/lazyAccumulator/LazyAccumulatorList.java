package objects.lazyAccumulator;

import objects.accumulator.Operation;
import objects.linkedList.List;
import objects.linkedList.Stack;

/**
 * Created by ksenia on 28.03.2017.
 */
public class LazyAccumulatorList {
    List list;
    int startValue;

    public LazyAccumulatorList(int startValue, List list) {
        this.startValue = startValue;
        this.list = list;
    }

    public void add(int value, Operation operation) {
        Item item = new Item(value, operation);
        list.add(item);
    }

    public int calculate() {
        for (Object o : list) {
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
