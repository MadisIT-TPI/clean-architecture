package io.reflectoring.buckpal.stock.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BuyStockTest {

    private Stock stock;
    @BeforeEach
    void setUp() {
        stock = new Stock(new Random().nextLong());
    }
    @Test
    @DisplayName("주식 금액과 수량이 주어졌을때 매수 주식을 생성한다.")
    void stockCreateTest() {
        // given
        final StockAmount count = new StockAmount(10L);
        final StockMoney money = new StockMoney(1000L);

        // when
        final BuyStock buyStock = new BuyStock(stock, count, money);

        // then
        assertThat(buyStock.getCount()).isEqualTo(count);
        assertThat(buyStock.getMoney()).isEqualTo(money);
    }

    @Test
    @DisplayName("수량*가격이 10,000,000 원을 넘게 구매할 수 없다")
    void stockCreateFailTest() {
        // given
        final StockAmount count = new StockAmount(10L);
        final StockMoney money = new StockMoney(10_000_000L);

        // when & then
        assertThatThrownBy(() -> new BuyStock(stock, count, money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid stock Total money : " + count.getAmount() * money.getMoney());
    }
}
