package io.reflectoring.buckpal.stock.domain;

import lombok.Getter;

@Getter
public class Stock {

    private final Long id;

    private Stock(Long id) {
        this.id = id;
    }

    public static Stock of(Long id) {
        return new Stock(id);
    }
}
