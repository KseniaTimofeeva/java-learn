package objects.shop;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ksenia on 05.04.2017.
 */
public class Utils {
    public static boolean checkRegExp(String s, String pattern) {
        s = s.trim().replaceAll("\\s+", " ");
        Pattern regexp = Pattern.compile(pattern);
        Matcher matcher = regexp.matcher(s);
        return matcher.matches();
    }
}
