package io.reflectoring.buckpal.stock.adapter.out.persistence;

import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<CompanyJpaEntity, Long> {
}
