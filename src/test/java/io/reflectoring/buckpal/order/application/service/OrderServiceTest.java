package io.reflectoring.buckpal.order.application.service;

import io.reflectoring.buckpal.order.application.port.in.OrderCommand;
import io.reflectoring.buckpal.order.application.port.out.CreateOrderPort;
import io.reflectoring.buckpal.order.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class OrderServiceTest {
    private final CreateOrderPort createOrderPort =
            Mockito.mock(CreateOrderPort.class);

    private final OrderService orderService = new OrderService(createOrderPort);

    @DisplayName("매수 주문 성공")
    @Test
    void orderSucceeds() {
        // given
        given(createOrderPort.createOrder(any(Order.class))).willReturn(any(Long.class));

        // when
        boolean success = orderService.order(OrderCommand.of(1, BigDecimal.valueOf(1_000))) != null;

        // then
        assertThat(success).isTrue();
    }
}
