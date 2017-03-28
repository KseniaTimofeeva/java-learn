package objects.lazyAccumulator;

import objects.accumulator.Operation;
import objects.linkedList.ArrayList;
import objects.linkedList.List;

/**
 * Created by ksenia on 28.03.2017.
 */
public class MainLazyAccumulatorList {
    public static void main(String[] args) {
        List list = new ArrayList();
        LazyAccumulatorList lazyAccumulatorList = new LazyAccumulatorList(0, list);

        lazyAccumulatorList.add(8, new Operation() {
            @Override
            public int apply(int a1, int a2) {
                return a1 + a2;
            }

            @Override
            public double apply(double a1, double a2) {
                return 0;
            }

            @Override
            public long apply(long a1, long a2) {
                return 0;
            }
        });

        lazyAccumulatorList.add(12, new Operation() {
            @Override
            public int apply(int a1, int a2) {
                return a1 - a2;
            }

            @Override
            public double apply(double a1, double a2) {
                return 0;
            }

            @Override
            public long apply(long a1, long a2) {
                return 0;
            }
        });

        System.out.println("Result: " + lazyAccumulatorList.calculate());
    }
}
