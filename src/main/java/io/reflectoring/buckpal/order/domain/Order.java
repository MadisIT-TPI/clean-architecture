package io.reflectoring.buckpal.order.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
public class Order {
    @EqualsAndHashCode.Include
    private Long id;
    private int quantity;
    private BigDecimal price;

    private Order(int quantity, BigDecimal price) {
        validateQuantity(quantity);
        validatePrice(price);
        validateTotalPrice(quantity, price);
        this.quantity = quantity;
        this.price = price;
    }

    public static Order order(int quantity, BigDecimal price) {
        return new Order(quantity, price);
    }

    private void validateQuantity(int quantity) {
        if (quantity < 1 || quantity > 10) {
            throw new IllegalArgumentException("매수 수량은 최소 1, 최대 10 까지 설정 할 수 있습니다.");
        }
    }

    private void validatePrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("매수 가격은 0 이상이어야 합니다..");
        }
    }

    private void validateTotalPrice(int quantity, BigDecimal price) {
        if (price.multiply(BigDecimal.valueOf(quantity)).compareTo(BigDecimal.valueOf(10_000_000)) > 0) {
            throw new IllegalArgumentException("매수 수량 * 가격이 10,000,000 원을 넘게 구매할 수 없습니다.");
        }
    }
}
