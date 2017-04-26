package objects.reflection.diContext;

import objects.reflection.toString.StringUtils;

/**
 * Created by ksenia on 26.04.2017.
 */
public class DIContextExample {
    public static void main(String[] args) {
        DIContext diContext = new DIContext();

        B b = (B) diContext.get("objects.reflection.diContext.B");

        System.out.println(StringUtils.toString(b));

        String randomString = b.getIntrf().getValue();
        System.out.println(randomString);

        Intrf i = (Impl) diContext.get("objects.reflection.diContext.Impl");

        System.out.println(i == b.getIntrf());
    }
}
