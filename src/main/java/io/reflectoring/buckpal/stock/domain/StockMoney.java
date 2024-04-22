package io.reflectoring.buckpal.stock.domain;

import lombok.Getter;

@Getter
public class StockMoney {

    private final Long money;

    public StockMoney(Long money) {
        if (money < 0) {
            throw new IllegalArgumentException("Invalid stock money: " + money);
        }
        this.money = money;
    }
}
