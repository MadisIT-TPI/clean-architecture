package io.reflectoring.buckpal.order.application.service;

import io.reflectoring.buckpal.common.UseCase;
import io.reflectoring.buckpal.order.application.port.in.OrderCommand;
import io.reflectoring.buckpal.order.application.port.in.OrderUseCase;
import io.reflectoring.buckpal.order.application.port.out.CreateOrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
class OrderService implements OrderUseCase {
    private final CreateOrderPort createOrderPort;

    @Override
    public Long order(OrderCommand command) {
        // TODO 매수 가능한 잔액이 있어야 매수가 가능하다.
        return createOrderPort.createOrder(command.toOrder());
    }
}
