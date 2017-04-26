package objects.reflection.diContext;

/**
 * Created by ksenia on 26.04.2017.
 */
public class A {
    private String str;

    @Resource(Impl.class)
    private Intrf i;

    public Intrf getI() {
        return i;
    }
}
