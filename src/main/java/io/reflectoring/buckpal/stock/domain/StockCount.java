package io.reflectoring.buckpal.stock.domain;

import lombok.Getter;

@Getter
public class StockCount {

    private final Long count;
    private static final Long MIN_COUNT = 0L;
    private static final Long MAX_COUNT = 10L;

    public StockCount(Long count) {
        if (count <= MIN_COUNT || count > MAX_COUNT) {
            throw new IllegalArgumentException("Invalid stock count: " + count);
        }
        this.count = count;
    }
}
