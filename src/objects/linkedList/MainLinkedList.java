package objects.linkedList;

import java.io.File;
import java.util.Random;

/**
 * Created by ksenia on 24.03.2017.
 */
public class MainLinkedList {
    public static void main(String[] args) {
//        LinkedList
        List<Integer> list = new LinkedList<>();
        List<Integer> list2 = new LinkedList<>();
        List<Integer> list3 = new LinkedList<>();
        List<Integer> list4 = new LinkedList<>();

        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            list.add(rnd.nextInt(20));
            list2.add(i);
            list3.add(i);
            list4.add(i + 5);
        }
        list4.add(-2);
        list4.add(-3);

        ArrayList<Integer> arrayList = new ArrayList<>(5);
        for (int i = 0; i < 10; i++) {
            arrayList.add(i);
        }

        System.out.println(list.toString());
        System.out.print("Get:" + list.get(0) + " " + list.get(9) + " " + list.get(5) + "\n");
//        System.out.println(list.get(-3));
//        System.out.println(list.get(15));
        System.out.print("Remove: " + list.remove(9) + " " + list.remove(5) + " " + list.remove(0) + "\n");
        System.out.println(list.toString());

//        Stack
        Stack<Integer> stack = new LinkedList<>();
        for (int i = 10; i < 20; i++) {
            stack.push(i);
        }
        System.out.println("\nStack poll: " + stack.poll() + " " + stack.poll() + " " + stack.poll() + "\n");


//        UtilityClass
//        find
        System.out.println("find: " + UtilityClass.find(new Predicate() {
            @Override
            public boolean apply(Object o) {
                return (int) o == 3;
            }
        }, list) + "\n");

//        filter
        List filteredList = UtilityClass.filter(new Predicate() {
            @Override
            public boolean apply(Object o) {
                return o.toString().length() > 1;
            }
        }, list);
        System.out.println("Filter (length > 1): " + filteredList.toString());

//        transform
        List transformedList = UtilityClass.transform(new Transformer() {
            @Override
            public Object apply(Object o) {
                return o.toString() + o.toString();
            }
        }, list);
        System.out.println("Transformed: " + transformedList.toString());

        //equals & hashCode
        System.out.println(list2.equals(list3));
        System.out.println(list.equals(list3));
        System.out.println(list2.equals(arrayList));

        System.out.println("hc = " + list.hashCode());
        System.out.println("hc = " + list2.hashCode());
        System.out.println("hc = " + list3.hashCode() + "\n");


//        diff & intersect
        System.out.println("Difference & intersect");
        System.out.println(list2.toString());
        System.out.println(list4.toString());

        List list5 = UtilityClass.difference(list2, list4, null);
        System.out.println("diff (null): " + list5.toString());

        list5 = UtilityClass.intersect(list2, list4, null);
        System.out.println("ints (null): " + list5.toString());
        list5 = UtilityClass.intersect(list2, list4, new Predicate2() {
            @Override
            public boolean apply(Object o1, Object o2) {
                return Math.abs((int)o1) == Math.abs((int)o2);
            }
        });
        System.out.println("ints (abs): " + list5.toString());

//        Array into list
        Integer[] array = new Integer[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 10;
        }
        list5 = UtilityClass.toList(array);
        System.out.println("ToList: " + list5.toString() + "\n");


//        FILES
        System.out.println("Files");
        File dir1 = new File("d:/javaProjectsTest/dir1");
        File dir2 = new File("d:/javaProjectsTest/dir2");
        List files1 = UtilityClass.toList(dir1.listFiles());
        List files2 = UtilityClass.toList(dir2.listFiles());

//        Duplicated files
        System.out.println("Duplicated files by 'name':");
        UtilityClass.listOutput(UtilityClass.intersect(files1, files2, new Predicate2() {
            @Override
            public boolean apply(Object o1, Object o2) {
                return ((File)o1).getName().equals(((File)o2).getName());
            }
        }));

        System.out.println("\nDuplicated files by 'name' and '.java':");
        UtilityClass.listOutput(UtilityClass.intersect(files1, files2, new Predicate2() {
            @Override
            public boolean apply(Object o1, Object o2) {
                return ((File)o1).getName().equals(((File)o2).getName()) && ((File)o1).getName().endsWith(".java") && ((File)o2).getName().endsWith(".java");
            }
        }));

//        Search file
        System.out.println("\nSearch file 'Lektsia_4.pdf':");
        System.out.println(UtilityClass.find(new Predicate() {
            @Override
            public boolean apply(Object o) {
                return ((File)o).getName().equals("Lektsia_4.pdf");
            }
        }, files1) + "\n");

//        Filtered by '.java'
        System.out.println("Filtered by '.java':");
        UtilityClass.listOutput(UtilityClass.filter(new Predicate() {
            @Override
            public boolean apply(Object o) {
                return ((File)o).getName().endsWith(".java");
            }
        }, files1));
        System.out.println();

//        filtered by 'size > 100Kb'
        System.out.println("Filtered by 'size > 100Kb':");
        UtilityClass.listOutput(UtilityClass.filter(new Predicate() {
            @Override
            public boolean apply(Object o) {
                return ((File)o).length() > 102400;
            }
        }, files1));
    }
}
