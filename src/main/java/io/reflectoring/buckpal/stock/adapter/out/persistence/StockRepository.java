package io.reflectoring.buckpal.stock.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface StockRepository extends JpaRepository<StockJpaEntity, Long> {
}
