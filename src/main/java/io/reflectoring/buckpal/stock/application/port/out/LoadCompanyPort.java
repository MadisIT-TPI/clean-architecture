package io.reflectoring.buckpal.stock.application.port.out;

import io.reflectoring.buckpal.stock.domain.Company;
import io.reflectoring.buckpal.stock.domain.Company.CompanyId;

public interface LoadCompanyPort {

    Company loadCompany(CompanyId companyId);
}
