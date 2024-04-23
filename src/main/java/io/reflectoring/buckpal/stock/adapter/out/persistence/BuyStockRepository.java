package io.reflectoring.buckpal.stock.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface BuyStockRepository extends JpaRepository<BuyStockJpaEntity, Long> {
}
