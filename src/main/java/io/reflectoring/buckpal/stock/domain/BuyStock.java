package io.reflectoring.buckpal.stock.domain;

import lombok.Getter;

@Getter
public class BuyStock {

    private static final long MAX_TOTAL_MONEY = 10_000_000L;
    private final Stock stock;
    private final StockAmount count;
    private final StockMoney money;

    public BuyStock(Stock stock, StockAmount count, StockMoney money) {
        this.validateStock(count, money);
        this.stock = stock;
        this.count = count;
        this.money = money;
    }

    private void validateStock(StockAmount count, StockMoney money) {
        final long totalMoney = count.getAmount() * money.getMoney();
        if (totalMoney > MAX_TOTAL_MONEY) {
            throw new IllegalArgumentException("Invalid stock Total money : " + totalMoney);
        }
    }
}
