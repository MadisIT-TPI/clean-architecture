package io.reflectoring.buckpal.stock.domain;

import lombok.Getter;

@Getter
public class StockMoney {

    private final Long money;
    private final static Long ZERO = 0L;
    public StockMoney(Long money) {
        if (money < ZERO) {
            throw new IllegalArgumentException("Invalid stock money: " + money);
        }
        this.money = money;
    }
}
