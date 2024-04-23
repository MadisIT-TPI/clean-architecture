package io.reflectoring.buckpal.order.adapter.out.persistence;

import io.reflectoring.buckpal.order.domain.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.Optional;

@DataJpaTest
@Import({OrderPersistenceAdapter.class})
class OrderPersistenceAdapterTest {
    @Autowired
    private OrderPersistenceAdapter adapter;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void createOrder() {
        Order order = Order.order(1, BigDecimal.valueOf(1_000));

        Long orderId = adapter.createOrder(order);

        Optional<OrderJpaEntity> orderJpaEntityOpt = orderRepository.findById(orderId);

        Assertions.assertThat(orderJpaEntityOpt.isPresent()).isTrue();
        Assertions.assertThat(orderJpaEntityOpt.get().getId()).isEqualTo(orderId);
    }
}
