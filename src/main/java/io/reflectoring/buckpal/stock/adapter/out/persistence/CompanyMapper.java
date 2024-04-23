package io.reflectoring.buckpal.stock.adapter.out.persistence;

import io.reflectoring.buckpal.account.domain.Account.AccountId;
import io.reflectoring.buckpal.stock.domain.Company;
import io.reflectoring.buckpal.stock.domain.Company.CompanyId;
import io.reflectoring.buckpal.stock.domain.Stock;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    Company mapToDomainEntity(CompanyJpaEntity company, Stock stock) {
        return Company.withId(
                new CompanyId(company.getId()),
                company.getName(),
                stock,
                new AccountId(company.getOwnerAccountId())
        );
    }
}
