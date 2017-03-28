package objects.lazyAccumulator;

import objects.accumulator.Operation;
import objects.linkedList.LinkedList;
import objects.linkedList.Stack;

/**
 * Created by ksenia on 29.03.2017.
 */
public class MainLazyAccumulatorStack {
    public static void main(String[] args) {
        Stack stack = new LinkedList();
        LazyAccumulatorStack lazyAccumulatorStack = new LazyAccumulatorStack(2, stack);

        lazyAccumulatorStack.add(0, new Operation() {
            @Override
            public int apply(int a1, int a2) {
                return a1 * a2;
            }

            @Override
            public double apply(double a1, double a2) {
                return a1 * a2;
            }

            @Override
            public long apply(long a1, long a2) {
                return a1 * a2;
            }
        });

        lazyAccumulatorStack.add(3, new Operation() {
            @Override
            public int apply(int a1, int a2) {
                return a1 - a2;
            }

            @Override
            public double apply(double a1, double a2) {
                return a1 - a2;
            }

            @Override
            public long apply(long a1, long a2) {
                return a1 - a2;
            }
        });

        lazyAccumulatorStack.add(3, new Operation() {
            @Override
            public int apply(int a1, int a2) {
                return (int)Math.pow(a1, a2);
            }

            @Override
            public double apply(double a1, double a2) {
                return Math.pow(a1, a2);
            }

            @Override
            public long apply(long a1, long a2) {
                return (long)Math.pow(a1, a2);
            }
        });

        System.out.println("Result: " + lazyAccumulatorStack.calculate());
    }
}
