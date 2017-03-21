package objects;

import java.util.Arrays;

/**
 * Created by ksenia on 21.03.2017.
 */
public class Library {
    private int qtyAllBooks;
    Book[] books;
    int[] qtyBook;

    public Library(int qtyAllBooks) {
        this.qtyAllBooks = qtyAllBooks;
        books = new Book[qtyAllBooks];          //каждой книге соответствует индекс
        qtyBook = new int[qtyAllBooks];         //каждому индексу соответствует кол-во в библиотеке
    }

    public void put(Book book, int qty) {
        if (book != null) {
            for (int i = 0; i < books.length; i++) {
                if (books[i].equals(book)) {
                    qtyBook[i] += qty;
                    return;
                }
            }
            System.out.println("Книга не из библиотеки");
        }
    }

    public int get(Book book, int qty) {
        if (book != null) {
            for (int i = 0; i < books.length; i++) {
                if (books[i].equals(book)) {
                    if (qtyBook[i] >= qty) {
                        qtyBook[i] -= qty;
                        return qty;
                    } else {
                        int temp = qtyBook[i];
                        qtyBook[i] = 0;
                        return temp;
                    }
                }
            }
            return -1;
        } else {
            return -1;
        }
    }
}
