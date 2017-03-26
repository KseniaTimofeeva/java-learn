package objects.accumulator;

/**
 * Created by ksenia on 22.03.2017.
 */
public class Accumulator {
    private double value;
    private Operation operation;
    private int[] values;
    private Operation[] operations;

    public Accumulator(double value, Operation operation) {
        this.value = value;
        this.operation = operation;
    }

    public Accumulator(int[] values, Operation[] operations) {
        this.values = values;
        this.operations = operations;
    }

    public int[] calculateArray(int a) {
        for (int i = 0; i < values.length; i++) {
            values[i] = operations[i].apply(values[i], a);
        }
        return values;
    }

    public int calculate(int a) {
        value = operation.apply(value, a);
        return (int) value;
    }

    public double calculate(double a) {
        value = operation.apply(value, a);
        return value;
    }

    public long calculate(long a) {
        value = operation.apply(value, a);
        return (long) value;
    }

    public double getValue() {
        return value;
    }
}
