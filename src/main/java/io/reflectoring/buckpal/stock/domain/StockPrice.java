package io.reflectoring.buckpal.stock.domain;

import io.reflectoring.buckpal.account.domain.Money;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StockPrice {

    List<Fluctuation> fluctuations;

    public Money get() {
        return null;
    }
}
