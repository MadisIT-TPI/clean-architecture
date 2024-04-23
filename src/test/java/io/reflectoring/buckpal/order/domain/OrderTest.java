package io.reflectoring.buckpal.order.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

class OrderTest {

    public static Stream<Arguments> provideInvalidQuantityAndPrice() {
        return Stream.of(
                Arguments.of(1, 10_000_001),
                Arguments.of(2, 5_000_001)
        );
    }

    public static Stream<Arguments> provideQuantityAndPrice() {
        return Stream.of(
                Arguments.of(1, 10_000_000),
                Arguments.of(10, 1_000_000)
        );
    }

    @DisplayName("매수 수량이 0보다 작으면 예외를 발생시킨다.")
    @ParameterizedTest(name = "{index} : {displayName}, quantity:{arguments}")
    @ValueSource(ints = {-2, -1, 0})
    void throwExceptionWhenQuantityLessOrEqualsZero(int quantity) {
        Assertions.assertThatThrownBy(() -> Order.order(quantity, BigDecimal.valueOf(1)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("매수 수량이 10보다 크면 예외를 발생시킨다.")
    @ParameterizedTest(name = "{index} : {displayName}, quantity:{arguments}")
    @ValueSource(ints = {11, 12})
    void throwExceptionWhenQuantityGreaterThanTen(int quantity) {
        Assertions.assertThatThrownBy(() -> Order.order(quantity, BigDecimal.valueOf(1)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("매수 가격이 0보다 작으면 예외를 발생시킨다.")
    @ParameterizedTest(name = "{index} : {displayName}, price:{arguments}")
    @ValueSource(ints = {-2, -1})
    void throwExceptionWhenPriceLessThanZero(int price) {
        Assertions.assertThatThrownBy(() -> Order.order(0, BigDecimal.valueOf(price)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("매수 수량 * 가격이 10_000_000원을 넘으면 예외가 발생한다.")
    @ParameterizedTest(name = "{index} : {displayName}, quantity:{0}, price:{1}")
    @MethodSource("provideInvalidQuantityAndPrice")
    void throwExceptionTotalPriceGreaterThan10_000_000(int quantity, int price) {
        Assertions.assertThatThrownBy(() -> Order.order(quantity, BigDecimal.valueOf(price)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("매수 주문 정상 생성 확인")
    @ParameterizedTest(name = "{index} : {displayName}, quantity:{0}, price:{1}")
    @MethodSource("provideQuantityAndPrice")
    void createOrder(int quantity, int price) {
        Order actual = Order.order(quantity, BigDecimal.valueOf(price));

        Assertions.assertThat(actual.getQuantity()).isEqualTo(quantity);
        Assertions.assertThat(actual.getPrice().compareTo(BigDecimal.valueOf(price))).isEqualTo(0);
    }
}
