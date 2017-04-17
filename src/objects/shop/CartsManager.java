package objects.shop;

import objects.linkedList.ArrayList;
import objects.linkedList.LinkedList;
import objects.linkedList.List;

/**
 * Created by ksenia on 02.04.2017.
 */
class CartsManager {
    private List<LinkedList<CartRecord>> allCarts;
    private StockManager stockManager;

    CartsManager(StockManager stockManager) {
        this.stockManager = stockManager;
        allCarts = new ArrayList<>();
    }

    public void createCartForNewUser() {
        allCarts.add(new LinkedList<>());
    }

    public boolean add(int userId, int productId, int qty) {
        if (qty == 0) {
            return false;
        }
        if (userId >= allCarts.size()) {
            return false;
        }
        Product product = stockManager.getProduct(productId);
        int currentQty = stockManager.checkCurrentQty(product);
        if (currentQty == 0) {
            return false;
        }
        int setQty;
        if (qty >= currentQty) {
            setQty = currentQty;
        } else {
            setQty = qty;
        }
        return allCarts.get(userId).add(new CartRecord(product, setQty));
    }

    public List<CartRecord> getUserCart(int userId) {
        return allCarts.get(userId);
    }

    public boolean set(int userId, Product product, int qty) {
        return allCarts.get(userId).set(getIndex(userId, product), new CartRecord(product, qty));
    }

    public boolean delete(int userId, Product product, int qty) {
        if (userId >= allCarts.size()) {
            return false;
        }
        if (qty == 0) {
            return false;
        }
        int currentQty = checkCurrentQty(userId, product);
        if (currentQty == 0) {
            return false;
        }
        if (qty >= currentQty) {
            allCarts.get(userId).remove(getIndex(userId, product));
            return true;
        } else {
            int setQty = currentQty - qty;
            return allCarts.get(userId).set(getIndex(userId, product), new CartRecord(product, setQty));
        }
    }

    private int getIndex(int userId, Product product) {
        int i = 0;
        for (CartRecord cartRecord : allCarts.get(userId)) {
            if (cartRecord.product.getId() == product.getId()) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public boolean clearCart(int userId) {
        return allCarts.set(userId, new LinkedList<>());
    }

    public double getTotalSum(int userId) {
        double totalSum = 0;
        for (CartRecord cartRecord : allCarts.get(userId)) {
            totalSum += cartRecord.product.getPrice() * cartRecord.qty;
        }
        return totalSum;
    }

    public String get(int userId) {
        StringBuilder stringBuilder = new StringBuilder();
        double totalSum = getTotalSum(userId);

        for (CartRecord cartRecord : allCarts.get(userId)) {
            stringBuilder.append("{").append(cartRecord.product.toString()).append("\t");
            stringBuilder.append(cartRecord.qty).append(" шт").append("\t");
            stringBuilder.append(cartRecord.product.getPrice() * cartRecord.qty).append(" руб}\n");
        }
        stringBuilder.append("-----------------\n");
        stringBuilder.append("Итого: ").append(totalSum).append("руб\n");
        return stringBuilder.toString();
    }

    public int checkCurrentQty(int currentUserId, Product product) {
        for (CartRecord cartRecord : allCarts.get(currentUserId)) {
            if (cartRecord.product.equals(product)) {
                return cartRecord.qty;
            }
        }
        return 0;
    }

    static class CartRecord {
        private Product product;
        private int qty;

        private CartRecord(Product product, int qty) {
            this.product = product;
            this.qty = qty;
        }

        Product getProduct() {
            return product;
        }

        int getQty() {
            return qty;
        }

    }
}
