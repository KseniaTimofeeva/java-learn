package objects.reflection.diContext;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ksenia on 26.04.2017.
 */
public class DIContext {
    private Map<Class, Object> createdObjects = new HashMap<>();


    public Object get(String className) {
        Class objClass = null;
        try {
            objClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (objClass == null) {
            return null;
        }

        Object res = createdObjects.get(objClass);
        if (res == null) { //TODO потерялись синглтоны
            try {
                res = objClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        Field[] declaredFields = objClass.getDeclaredFields();

        for (Field field : declaredFields) {
            Resource annotation = field.getAnnotation(Resource.class);

            if (annotation != null) {

                Class depType = annotation.value();
                if (depType.equals(Object.class)) {
                    depType = field.getType();
                }
                boolean singleton = annotation.singleton();

                Object dependObj = createdObjects.get(depType);

                if (!singleton || dependObj == null) {

                    dependObj = get(depType.getTypeName());
                    createdObjects.putIfAbsent(depType, dependObj);
                }

                field.setAccessible(true);
                try {
                    field.set(res, dependObj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return res;

    }



}
