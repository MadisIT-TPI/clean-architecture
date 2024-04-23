package io.reflectoring.buckpal.stock.adapter.out.persistence;

import io.reflectoring.buckpal.common.PersistenceAdapter;
import io.reflectoring.buckpal.stock.application.port.out.LoadCompanyPort;
import io.reflectoring.buckpal.stock.domain.Company;
import io.reflectoring.buckpal.stock.domain.Company.CompanyId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class CompanyPersistenceAdapter implements LoadCompanyPort {

    @Override
    public Company loadCompany(CompanyId companyId) {
        return null;
    }
}
