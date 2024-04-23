package io.reflectoring.buckpal.stock.domain;

import lombok.Getter;

@Getter
public class StockMoney {

    private final Long money;
    private final static Long ZERO = 0L;
    private StockMoney(Long money) {
        this.money = money;
    }

    public static StockMoney of(Long money) {
        if (money < ZERO) {
            throw new IllegalArgumentException("Invalid stock money: " + money);
        }
        return new StockMoney(money);
    }
}
