package objects.shop;

import objects.linkedList.List;

import java.util.Date;

/**
 * Created by ksenia on 02.04.2017.
 */
public class Transaction {
    private static int nextTransactionId;
    private int id;
    private int userId;
    private Date date;
    private List products;
    double totalSum;

    public Transaction(int userId, List products, double totalSum) {
        id = nextTransactionId;
        this.userId = userId;
        this.products = products;
        date = new Date();
        this.totalSum = totalSum;
        nextTransactionId++;
    }
}
