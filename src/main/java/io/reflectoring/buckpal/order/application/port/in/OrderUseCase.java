package io.reflectoring.buckpal.order.application.port.in;

public interface OrderUseCase {
    boolean order(OrderCommand command);
}
