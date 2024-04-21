package io.reflectoring.buckpal.stock.domain;

import io.reflectoring.buckpal.account.domain.Money;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Fluctuation {

    private final Money money;
    private final LocalDateTime timestamp;
}
