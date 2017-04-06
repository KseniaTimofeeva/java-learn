package objects.linkedList;

/**
 * Created by ksenia on 27.03.2017.
 */
public class UtilityClass {
    public static <T> T find(Predicate<T> pred, List<T> list) {
        if (list == null) {
            return null;
        }
        for (T o : list) {
            if (pred.apply(o)) {
                return o;
            }
        }
        return null;
    }

    public static <T> List<T> filter(Predicate<T> pred, List<T> list) {
        if (list == null) {
            return null;
        }
        List<T> newList = getNewList(list);
        for (T o : list) {
            if (pred.apply(o)) {
                newList.add(o);
            }
        }
        return newList;
    }

    public static <T, R> List<R> transform(Transformer<T, R> trans, List<T> list) {
        if (list == null) {
            return null;
        }
        List<R> newList = getNewList(list);
        for (T o : list) {
            newList.add(trans.apply(o));
        }
        return newList;
    }

    private static <T, R> List<R> getNewList(List<T> list) {
        if (list instanceof LinkedList) {
            return new LinkedList<>();
        }
        return new ArrayList<>();
    }

    private static <T> List<T> diffIntersectNewList(List<T> list1, List<T> list2, Predicate2<T> predicate2, boolean intersect) {
        if (list1 == null) {
            return null;
        }
        if (list2 == null) {
            if (intersect) {
                return null;
            }
            return list1;
        }
        List<T> newList = new LinkedList<>();
        boolean isEquals;
        boolean condition;
        for (T o1 : list1) {
            isEquals = false;
            for (T o2 : list2) {
                if (predicate2 == null) {
                    condition = o1.equals(o2);
                } else {
                    condition = predicate2.apply(o1, o2);
                }
                if (condition) {
                    isEquals = true;
                    break;
                }
            }
            if (!intersect && !isEquals) {
                newList.add(o1);
            } else {
                if (isEquals) {
                    newList.add(o1);
                }
            }
        }
        return newList;
    }

    //intersect = false
    public static <T> List<T> difference(List<T> list1, List<T> list2, Predicate2<T> predicate2) {
        return diffIntersectNewList(list1, list2, predicate2, false);
    }

    //    intersect = true
    public static <T> List<T> intersect(List<T> list1, List<T> list2, Predicate2<T> predicate2) {
        return diffIntersectNewList(list1, list2, predicate2, true);
    }

    public static <T> List<T> toList(T[] objects) {
        if (objects == null) {
            return null;
        }
        List<T> newList = new LinkedList<>();
        for (T o : objects) {
            newList.add(o);
        }
        return newList;
    }

    public static void listOutput(List list) {
        if (list == null) {
            System.out.println("Empty list");
            System.exit(-1);
        }
        StringBuilder strBuild = new StringBuilder();
        strBuild.append("[");
        int i = 0;
        for (Object o : list) {
            if (i == 0) {
                if (i == list.getSize() - 1) {
                    strBuild.append(o.toString());
                } else {
                    strBuild.append(o.toString()).append(",").append("\n");
                }
            } else if (i == list.getSize() - 1) {
                strBuild.append(" ").append(o.toString());
            } else {
                strBuild.append(" ").append(o.toString()).append(",").append("\n");
            }
            i++;
        }
        strBuild.append("]");
        System.out.println(strBuild.toString());
    }

}
