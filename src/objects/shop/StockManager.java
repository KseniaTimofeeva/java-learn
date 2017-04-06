package objects.shop;

import objects.linkedList.ArrayList;
import objects.linkedList.List;

/**
 * Created by ksenia on 03.04.2017.
 */
class StockManager {
    private List<StockRecord> stock;

    StockManager() {
        stock = new ArrayList<>();
    }

    public boolean add(Product product, int qty) {
        if (qty < 0) {
            return false;
        }
        return stock.add(new StockRecord(product, qty));
    }

    public Product getProduct(int index) {
        return stock.get(index).product;
    }

    public String stockOutput() {
        StringBuilder stringBuilder = new StringBuilder();
        for (StockRecord stockRecord : stock) {
            if (stockRecord.qty > 0) {
                stringBuilder.append("{").append(stockRecord.product.toString()).append("\t");
                stringBuilder.append(stockRecord.qty).append(" шт").append("}\n");
            }
        }
        return stringBuilder.toString();
    }

    public int checkCurrentQty(Product product) {
        if (product.getId() >= stock.getSize()) {
            return 0;
        }
        return stock.get(product.getId()).qty;
    }

    public boolean decreaseProductQty(Product product, int qty) {
        if (qty == 0) {
            return false;
        }
        int currentQty = checkCurrentQty(product);
        if (currentQty == 0) {
            return false;
        }
        int setQty;
        if (qty >= currentQty) {
            setQty = 0;
        } else {
            setQty = currentQty - qty;
        }
        return stock.set(product.getId(), new StockRecord(product, setQty));
    }

    private static class StockRecord {
        Product product;
        int qty;

        private StockRecord(Product product, int qty) {
            this.product = product;
            this.qty = qty;
        }
    }

}
