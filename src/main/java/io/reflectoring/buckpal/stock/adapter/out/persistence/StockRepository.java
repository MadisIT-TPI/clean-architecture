package io.reflectoring.buckpal.stock.adapter.out.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StockRepository extends CrudRepository<StockJpaEntity, Long> {

    Optional<StockJpaEntity> findByCompanyId(Long companyId);
}
