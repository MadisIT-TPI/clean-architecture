package io.reflectoring.buckpal.stock.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StockMoneyTest {

    @Test
    @DisplayName("주식 가격은 0보다 커야 한다.")
    void stockMoneyValidateTest() {
        // given
        final long money = 1000L;

        // when
        final StockMoney stockMoney = new StockMoney(money);

        // then
        assertThat(stockMoney.getMoney()).isEqualTo(money);
    }

    @Test
    @DisplayName("주식 가격이 0보다 작으면 안된다.")
    void stockMoneyValidateFailTest() {
        assertThatThrownBy(() -> new StockMoney(-1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid stock money: -1");
    }
}
