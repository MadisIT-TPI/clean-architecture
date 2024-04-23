package io.reflectoring.buckpal.order.adapter.out.persistence;

import io.reflectoring.buckpal.common.PersistenceAdapter;
import io.reflectoring.buckpal.order.application.port.out.CreateOrderPort;
import io.reflectoring.buckpal.order.domain.Order;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
class OrderPersistenceAdapter implements CreateOrderPort {
    private final OrderRepository orderRepository;

    @Override
    public Long createOrder(Order order) {
        return orderRepository.save(mapToJpaEntity(order)).getId();
    }

    private OrderJpaEntity mapToJpaEntity(Order order) {
        return OrderJpaEntity.of(order.getId(),
                order.getQuantity(),
                order.getPrice());
    }
}
