package io.reflectoring.buckpal.stock.domain;

import io.reflectoring.buckpal.account.domain.Account;
import io.reflectoring.buckpal.account.domain.ActivityWindow;
import io.reflectoring.buckpal.account.domain.Money;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Company {

    private final CompanyId id;

    private final String name;

    @Getter
    private final Stock stock;

    @Getter
    private final Account.AccountId ownerAccountId;

    public static Company withId(
            Company.CompanyId companyId,
            String name,
            Account.AccountId ownerAccountId
    ) {
        return new Company(
                companyId,
                name,
                null,
                ownerAccountId
        );
    }

    @Value
    public static class CompanyId {
        Long value;
    }

}
