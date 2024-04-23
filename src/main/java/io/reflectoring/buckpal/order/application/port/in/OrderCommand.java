package io.reflectoring.buckpal.order.application.port.in;

import io.reflectoring.buckpal.common.SelfValidating;
import io.reflectoring.buckpal.order.domain.Order;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class OrderCommand extends SelfValidating<OrderCommand> {
    @NotNull
    @Min(value = 1)
    @Max(value = 10)
    private int quantity;

    @NotNull
    @DecimalMax(value = "10000000")
    private BigDecimal price;

    private OrderCommand(int quantity, BigDecimal price) {
        this.quantity = quantity;
        this.price = price;
        this.validateSelf();
        this.validateTotalPrice(quantity, price);
    }

    public static OrderCommand of(int quantity, BigDecimal price) {
        return new OrderCommand(quantity, price);
    }

    private void validateTotalPrice(int quantity, BigDecimal price) {
        if (price.multiply(BigDecimal.valueOf(quantity)).compareTo(BigDecimal.valueOf(10_000_000)) > 0) {
            throw new IllegalArgumentException("매수 수량 * 가격이 10,000,000 원을 넘게 구매할 수 없습니다.");
        }
    }

    public Order toOrder() {
        return Order.order(quantity, price);
    }
}
