package io.reflectoring.buckpal.stock.domain;

import io.reflectoring.buckpal.account.domain.Money;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Stock {

    private final StockId id;

    private StockPrice stockPrice;

    private List<Share> shares;

    @Value
    public static class StockId {
        Long value;
    }
}
