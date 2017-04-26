package objects.reflection.toString;

import com.itmo.iostreams.serial.print.Message;
import objects.patterns.observer.Station;

/**
 * Created by ksenia on 25.04.2017.
 */
public class ReflectionExamples {

    public static void main(String[] args) {
        System.out.println(StringUtils.toString(new Message(System.currentTimeMillis(), "sender", "hello")));

        System.out.println(StringUtils.toString(new Station()));
    }
}
