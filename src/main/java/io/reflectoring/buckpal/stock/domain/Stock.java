package io.reflectoring.buckpal.stock.domain;

import lombok.Getter;

@Getter
public class Stock {

    private final Long id;

    public Stock(Long id) {
        this.id = id;
    }
}
