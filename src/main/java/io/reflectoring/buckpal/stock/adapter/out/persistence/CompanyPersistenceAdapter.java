package io.reflectoring.buckpal.stock.adapter.out.persistence;

import io.reflectoring.buckpal.common.PersistenceAdapter;
import io.reflectoring.buckpal.stock.application.port.out.LoadCompanyPort;
import io.reflectoring.buckpal.stock.domain.Company;
import io.reflectoring.buckpal.stock.domain.Company.CompanyId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class CompanyPersistenceAdapter implements LoadCompanyPort {

    private final CompanyRepository companyRepository;
    private final StockRepository stockRepository;
    private final CompanyMapper companyMapper;
    private final StockMapper stockMapper;

    @Override
    public Company loadCompany(CompanyId companyId) {
        CompanyJpaEntity company = companyRepository.findById(companyId.getValue())
                .orElseThrow();
        StockJpaEntity stock = stockRepository.findByCompanyId(companyId.getValue())
                .orElseThrow();

        return companyMapper.mapToDomainEntity(
                company,
                stockMapper.mapToDomainEntity(stock)
        );
    }
}
