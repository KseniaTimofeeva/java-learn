package objects.linkedList;

/**
 * Created by ksenia on 27.03.2017.
 */
public class UtilityClass {
    public static Object find(Predicate pred, List list) {
        if (list == null) {
            return null;
        }
        for (Object o : list) {
            if (pred.apply(o)) {
                return o;
            }
        }
        return null;
    }

    public static List filter(Predicate pred, List list) {
        if (list == null) {
            return null;
        }
        List newList = getNewList(list);
        for (Object o : list) {
            if (pred.apply(o)) {
                newList.add(o);
            }
        }
        return newList;
    }

    public static List transform(Transformer trans, List list) {
        if (list == null) {
            return null;
        }
        List newList = getNewList(list);
        for (Object o : list) {
            newList.add(trans.apply(o));
        }
        return newList;
    }

    private static List getNewList(List list) {
        if (list instanceof LinkedList) {
            return new LinkedList();
        }
        return new ArrayList();
    }

    private static List diffIntersectNewList(List list1, List list2, Predicate2 predicate2, boolean intersect) {
        if (list1 == null) {
            return null;
        }
        if (list2 == null) {
            if (intersect) {
                return null;
            }
            return list1;
        }
        List newList = new LinkedList();
        boolean isEquals;
        boolean condition;
        for (Object o1 : list1) {
            isEquals = false;
            for (Object o2 : list2) {
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
    public static List difference(List list1, List list2, Predicate2 predicate2) {
        return diffIntersectNewList(list1, list2, predicate2, false);
    }

    //    intersect = true
    public static List intersect(List list1, List list2, Predicate2 predicate2) {
        return diffIntersectNewList(list1, list2, predicate2, true);
    }

    public static List toList(Object[] objects) {
        if (objects == null) {
            return null;
        }
        List newList = new LinkedList();
        for (Object o : objects) {
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
