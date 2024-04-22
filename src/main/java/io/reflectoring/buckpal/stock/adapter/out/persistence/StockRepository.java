package io.reflectoring.buckpal.stock.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockJpaEntity, Long> {
}
