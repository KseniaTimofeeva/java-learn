package objects;

/**
 * Created by ksenia on 20.03.2017.
 */
public class TestObjects {
    public static void main(String[] args) {
//       Adder
        Adder adder = new Adder(10);
        adder.increment();
        System.out.println(adder.getValue());
        adder.add(10);
        System.out.println(adder.getValue());
        System.out.println();

//        LinkedList
        LinkedList list = new LinkedList();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        System.out.println(list.get(0));
        System.out.println(list.get(9));
        System.out.println(list.get(5));
//        System.out.println(list.get(-3));
//        System.out.println(list.get(15));
        System.out.println();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println();

        list.remove(9);
        list.remove(5);
        list.remove(0);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

//            Библиотека
        int x = 15;
        Library library = new Library(x);
        //заполняем библиотеку книгами
        for (int i = 0; i < 15; i++) {
            library.books[i] = new Book(i + "", i + "", i);
            library.qtyBook[i] = 5;
        }

        for (int i = 0; i < library.qtyBook.length; i++) {
            System.out.print(library.qtyBook[i] + " ");
        }
        System.out.println();

        Book bookAtHome = new Book("0", "0", 0);
        library.put(bookAtHome, 2);
        bookAtHome = new Book("14", "14", 14);
        library.put(bookAtHome, 1);
//        bookAtHome = new Book("15", "15", 15);
//        library.put(bookAtHome, 1);

        for (int i = 0; i < library.qtyBook.length; i++) {
            System.out.print(library.qtyBook[i] + " ");
        }
        System.out.println();

        int result;
        bookAtHome = new Book("0", "0", 0);
        result = library.get(bookAtHome, 2);
        System.out.println(result);
        bookAtHome = new Book("14", "14", 14);
        result = library.get(bookAtHome, 7);
        System.out.println(result);
        bookAtHome = new Book("15", "15", 15);
        result = library.get(bookAtHome, 1);
        System.out.println(result);

        for (int i = 0; i < library.qtyBook.length; i++) {
            System.out.print(library.qtyBook[i] + " ");
        }
        System.out.println();
    }

}
