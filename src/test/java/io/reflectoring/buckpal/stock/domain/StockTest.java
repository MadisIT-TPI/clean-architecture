package io.reflectoring.buckpal.stock.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StockTest {

    @Test
    @DisplayName("주식 금액과 수량이 주어졌을때 주식을 생성한다.")
    void stockCreateTest() {
        // given
        final StockCount count = new StockCount(10L);
        final StockMoney money = new StockMoney(1000L);

        // when
        final Stock stock = new Stock(count, money);

        // then
        assertThat(stock.getCount()).isEqualTo(count);
        assertThat(stock.getMoney()).isEqualTo(money);
    }

    @Test
    @DisplayName("수량*가격이 10,000,000 원을 넘게 구매할 수 없다")
    void stockCreateFailTest() {
        // given
        final StockCount count = new StockCount(10L);
        final StockMoney money = new StockMoney(10_000_000L);

        // when & then
        assertThatThrownBy(() -> new Stock(count, money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid stock Total money : " + count.getCount() * money.getMoney());
    }
}
