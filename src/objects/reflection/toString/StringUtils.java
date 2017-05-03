package objects.reflection.toString;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.AbstractCollection;
import java.util.AbstractMap;

/**
 * Created by ksenia on 24.04.2017.
 */
public final class StringUtils {

    private StringUtils() {

    }

    public static String toString(Object obj) {

        StringBuilder sb = new StringBuilder();

        if (obj != null) {

            Class objClass = obj.getClass();

            boolean hasToString = false;
            for (Method method : objClass.getDeclaredMethods()) {
                if (method.getName().equals("toString")) {
                    hasToString = true;
                    break;
                }
            }
            if (hasToString || (obj instanceof AbstractCollection) || (obj instanceof AbstractMap)) {
                sb.append(obj.toString());
            } else {
                sb.append("{").append(objClass.getSimpleName());

                Field[] declaredFields = objClass.getDeclaredFields();

                for (Field field : declaredFields) {
                    if (field.getAnnotation(Exclude.class) != null) {
                        continue;
                    }
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    sb.append(", ").append(field.getName()).append(" = ");
                    if (field.getType().isPrimitive()) {
                        try {
                            sb.append(field.get(obj));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (field.getType().isArray()) {
                            try {
                                sb.append("[");
                                for (int i = 0; i < Array.getLength(field.get(obj)); i++) {
                                    if (i != 0) {
                                        sb.append(", ");
                                    }
                                    sb.append(toString(Array.get(field.get(obj), i)));
                                }
                                sb.append("]");
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                sb.append(toString(field.get(obj)));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                sb.append("}");
            }
        } else {
            sb.append("null");
        }

        return sb.toString();

    }
}
