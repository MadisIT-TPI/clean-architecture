package io.reflectoring.buckpal.order.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface OrderRepository extends JpaRepository<OrderJpaEntity, Long> {
}
