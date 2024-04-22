package io.reflectoring.buckpal.stock.domain;

import lombok.Getter;

@Getter
public class StockAmount {

    private final Long amount;
    private static final Long MIN_COUNT = 0L;
    private static final Long MAX_COUNT = 10L;

    private StockAmount(Long amount) {
        if (amount <= MIN_COUNT || amount > MAX_COUNT) {
            throw new IllegalArgumentException("Invalid stock count: " + amount);
        }
        this.amount = amount;
    }

    public static StockAmount of(Long amount) {
        return new StockAmount(amount);
    }
}
