package objects.reflection.diContext;

/**
 * Created by ksenia on 26.04.2017.
 */
public class B {
    private int val;

    @Resource
    private A aVal;

    public Intrf getIntrf() {
        return aVal.getI();
    }
}
