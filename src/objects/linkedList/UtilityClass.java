package objects.linkedList;

/**
 * Created by ksenia on 27.03.2017.
 */
public class UtilityClass {
    public static Object find(Predicate pred, List list) {
        for (Object o : list) {
            if (pred.apply(o)) {
                return o;
            }
        }
        return null;
    }

    public static List filter(Predicate pred, List list) {
        List newList = getNewList(list);
        for (Object o : list) {
            if (pred.apply(o)) {
                newList.add(o);
            }
        }
        return newList;
    }

    public static List transform(Transformer trans, List list) {
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

    public static void listOutput(List list) {
        for (Object o : list) {
            System.out.print(o + " ");
        }
        System.out.println();
    }

}
