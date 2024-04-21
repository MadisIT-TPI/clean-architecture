package io.reflectoring.buckpal.stock.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Company {

    private final CompanyId id;

    private final String name;

    private final Stock stock;

    @Value
    public static class CompanyId {
        Long value;
    }

}
