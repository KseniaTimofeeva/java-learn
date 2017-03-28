package objects.library;

/**
 * Created by ksenia on 24.03.2017.
 */
public class Main {
    public static void main(String[] args) {
        int x = 15;
        Library library = new Library(x);
        //заполняем библиотеку книгами

        for (int i = 0; i < 15; i++) {
            Book book = new Book(i + "", i + "", i);
            int hc = Math.abs(book.hashCode()) % library.bookLinkedList.length;
            if (library.bookLinkedList[hc] == null) {
                library.bookLinkedList[hc] = new LinkedList();
            }
            library.bookLinkedList[hc].add(book, i);
        }

        for (int i = 0; i < 15; i++) {
            Book book = new Book(i + "", i + "", i);
            System.out.println(book.toString());
        }
        System.out.println();

        library.returnBook(new Book("0", "0", 0), 2);
        library.returnBook(new Book("14", "14", 14), 14);

        for (int i = 0; i < 15; i++) {
            Book book = new Book(i + "", i + "", i);
            System.out.print(library.get(book) + " ");
        }
        System.out.println();
        System.out.println();

        System.out.println("Выдано книг : " + library.giveBook(new Book("0", "0", 0), 3));
        System.out.println("Выдано книг : " + library.giveBook(new Book("14", "14", 14), 3));
        System.out.println("Выдано книг : " + library.giveBook(new Book("15", "15", 15), 3));
        System.out.println();

        for (int i = 0; i < 15; i++) {
            Book book = new Book(i + "", i + "", i);
            System.out.print(library.get(book) + " ");
        }
        System.out.println();
    }
}
