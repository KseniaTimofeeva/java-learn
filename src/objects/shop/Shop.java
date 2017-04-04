package objects.shop;

/**
 * Created by ksenia on 31.03.2017.
 */
public class Shop {
    private StockManager stockManager;
    private CartsManager cartsManager;
    private TransactionManager transactionManager;
    private static int nextUserId;
    private Double balance;
    private int currentUserId;

    public Shop() {
        System.out.println("\n------------------------------------");
        System.out.println("Добро пожаловать в Интернет-магазин!");
        System.out.println("------------------------------------");
        balance = new Double(0);
    }

    public boolean addProduct(Product product, int qty) {
        if (product == null) {
            return false;
        }
        if (stockManager == null) {
            stockManager = new StockManager();
        }
        return stockManager.add(product, qty);
    }

    public boolean addProductToCart(int productId, int qty) {
        return cartsManager.add(currentUserId, productId, qty);
    }

    public boolean deleteProductFromCart(int productId, int qty) {
        return cartsManager.delete(currentUserId, stockManager.getProduct(productId), qty);
    }

    public boolean buy(int totalSum) {
        return transactionManager.buy(currentUserId, totalSum);
    }

    public int addUser() {
        if (cartsManager == null) {
            cartsManager = new CartsManager(stockManager);
            if (transactionManager == null) {
                transactionManager = new TransactionManager(this, stockManager, cartsManager, balance);
            }
        }
        cartsManager.createCartForNewUser();
        currentUserId = nextUserId;
        return nextUserId++;
    }

    public void showCart() {
        System.out.println("-------------------------------");
        System.out.println("Список товаров в Вашей корзине:");
        System.out.println("-------------------------------");
        System.out.println(cartsManager.get(currentUserId));
    }

    public int getNextUserId() {
        return nextUserId;
    }

    public void setCurrentUserId(int currentUserId) {
        this.currentUserId = currentUserId;
    }

    public void stockOutput() {
        System.out.println("-------------------------------------");
        System.out.println("Список товаров / в наличии на складе:");
        System.out.println("-------------------------------------");
        System.out.println(stockManager.stockOutput());
    }

    public CartsManager getCartsManager() {
        return cartsManager;
    }

}

