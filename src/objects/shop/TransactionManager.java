package objects.shop;

import objects.linkedList.LinkedList;
import objects.linkedList.List;

/**
 * Created by ksenia on 03.04.2017.
 */
public class TransactionManager {
    private StockManager stockManager;
    private CartsManager cartsManager;
    private Double balance;
    private List<Transaction> allTransactions;
    private Shop shop;

    public TransactionManager(Shop shop, StockManager stockManager, CartsManager cartsManager, Double balance) {
        this.shop = shop;
        this.stockManager = stockManager;
        this.cartsManager = cartsManager;
        this.balance = balance;
        allTransactions = new LinkedList<>();
    }

    public boolean buy(int userId, int userTotalSum) {
        List<CartsManager.CartRecord> userCart = cartsManager.getUserCart(userId);
        if (userCart.size() == 0) {
            return false;
        }
        double totalSum = cartsManager.getTotalSum(userId);
        if (totalSum != userTotalSum) {
            return false;
        }

        boolean doTransaction = true;
        for (CartsManager.CartRecord cartRecord : userCart) {
            int currentCartQty = cartsManager.checkCurrentQty(userId, cartRecord.getProduct());
            int currentStockQty = stockManager.checkCurrentQty(cartRecord.getProduct());
            if (currentCartQty > currentStockQty) {
                doTransaction &= false;
                cartsManager.set(userId, cartRecord.getProduct(), currentStockQty); //заменили кол-во в корзине в соотв с кол-вом на складе
            } else {
                doTransaction &= true;
            }
        }
        if (!doTransaction) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("--------------------------------------------------------\n");
            stringBuilder.append("Некоторых товаров нет на складе в достаточном количестве\n");
            stringBuilder.append("Приносим извенения!\n");
            stringBuilder.append("Количество обновлено в соответствии с наличием товара на складе\n");
            System.out.println(stringBuilder.toString());
            shop.showCart();
            MenuHolder.cartMenu.print();
            return false;
        } else {
            boolean temp = true;
            for (CartsManager.CartRecord cartRecord : userCart) {
                temp &= stockManager.decreaseProductQty(cartRecord.getProduct(), cartRecord.getQty());
            }
            Transaction transaction = new Transaction(userId, userCart, totalSum);
            temp &= allTransactions.add(transaction);
            balance += totalSum;
            temp &= cartsManager.clearCart(userId);

            System.out.println("Баланс магазина: " + balance);
            return temp;
        }
    }


}
