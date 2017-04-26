package objects.reflection.diContext;

/**
 * Created by ksenia on 26.04.2017.
 */
public class Impl implements Intrf {
    private int k;

    @Override
    public String getValue() {
        return String.valueOf(Math.random());
    }
}
