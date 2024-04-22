package io.reflectoring.buckpal.stock.domain;

import lombok.Getter;

@Getter
public class Stock {

    private static final long MAX_TOTAL_MONEY = 10_000_000L;
    private final StockCount count;
    private final StockMoney money;

    public Stock(StockCount count, StockMoney money) {
        this.validateStock(count, money);
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
