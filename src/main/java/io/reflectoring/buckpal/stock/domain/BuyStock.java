package io.reflectoring.buckpal.stock.domain;

import lombok.Getter;

@Getter
public class BuyStock {

    private static final long MAX_TOTAL_MONEY = 10_000_000L;
    private final Stock stock;
    private final StockAmount count;
    private final StockMoney money;

    private BuyStock(Stock stock, StockAmount count, StockMoney money) {
        this.stock = stock;
        this.count = count;
        this.money = money;
    }

    public static BuyStock of(Stock stock, StockAmount count, StockMoney money) {
        final BuyStock buyStock = new BuyStock(stock, count, money);
        buyStock.validateStock();
        return buyStock;
    }

    public Long getCalculatedTotalMoney() {
        return count.getAmount() * money.getMoney();
    }

    private void validateStock() {
        final long totalMoney = getCalculatedTotalMoney();
        if (totalMoney > MAX_TOTAL_MONEY) {
            throw new IllegalArgumentException("Invalid stock Total money : " + totalMoney);
        }
    }
}
