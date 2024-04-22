package io.reflectoring.buckpal.stock.domain;

import lombok.Getter;

@Getter
public class BuyStock {

    private static final long MAX_TOTAL_MONEY = 10_000_000L;
    private final Stock stock;
    private final StockCount count;
    private final StockMoney money;

    public BuyStock(Stock stock, StockCount count, StockMoney money) {
        this.validateStock(count, money);
        this.stock = stock;
        this.count = count;
        this.money = money;
    }

    private void validateStock(StockCount count, StockMoney money) {
        final long totalMoney = count.getCount() * money.getMoney();
        if (totalMoney > MAX_TOTAL_MONEY) {
            throw new IllegalArgumentException("Invalid stock Total money : " + totalMoney);
        }
    }
}
