package io.reflectoring.buckpal.order.application.port.out;

import io.reflectoring.buckpal.order.domain.Order;

public interface CreateOrderPort {
    Long createOrder(Order order);
}
