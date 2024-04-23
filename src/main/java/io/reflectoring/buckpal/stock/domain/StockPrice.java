package io.reflectoring.buckpal.stock.domain;

import io.reflectoring.buckpal.account.domain.Money;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StockPrice {

    List<Fluctuation> fluctuations;

    public Money getCurrentPrice() {
        return fluctuations.stream()
                .max(Comparator.comparing(Fluctuation::getTimestamp))
                .orElseThrow(IllegalStateException::new)
                .getMoney();
    }

    public static StockPrice of(List<Fluctuation> fluctuations) {
        return new StockPrice(fluctuations);
    }
}
