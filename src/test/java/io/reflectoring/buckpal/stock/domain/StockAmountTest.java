package io.reflectoring.buckpal.stock.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StockAmountTest {

    @DisplayName("매수 수량은 최소 1, 최대 10 까지 설정 할 수 있다.")
    @Test
    void stockCountValidateTest () {
        // given
        final long count = new Random().nextInt(9) + 1;

        // when
        final StockAmount stockCount = StockAmount.of(count);

        // then
        assertThat(stockCount.getAmount()).isEqualTo(count);
    }

    @DisplayName("매수 수량이 0이거나 10을 넘으면 안된다.")
    @ParameterizedTest
    @ValueSource(longs = {0, 11})
    void stockCountValidateFailTest (Long value) {
        assertThatThrownBy(() -> StockAmount.of(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid stock count: " + value);
    }
}
