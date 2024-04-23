package io.reflectoring.buckpal.order.application.port.in;

public interface OrderUseCase {
    Long order(OrderCommand command);
}
