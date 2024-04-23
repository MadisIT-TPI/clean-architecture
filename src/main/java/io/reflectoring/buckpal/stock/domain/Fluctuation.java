package io.reflectoring.buckpal.stock.domain;

import io.reflectoring.buckpal.account.domain.Money;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Fluctuation {

    @Getter
    private final Money money;

    @Getter
    private final LocalDateTime timestamp;

    public static Fluctuation of(Money money, LocalDateTime timestamp) {
        return new Fluctuation(money, timestamp);
    }
}
