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
        stock = Stock.of(new Random().nextLong());
    }

    @Test
    @DisplayName("주식 금액과 수량이 주어졌을때 매수 주식을 생성한다.")
    void stockCreateTest() {
        // given
        final StockAmount count = StockAmount.of(10L);
        final StockMoney money = StockMoney.of(1000L);

        // when
        final BuyStock buyStock = BuyStock.of(stock, count, money);

        // then
        assertThat(buyStock.getCount()).isEqualTo(count);
        assertThat(buyStock.getMoney()).isEqualTo(money);
    }

    @Test
    @DisplayName("수량*가격이 10,000,000 원을 넘게 구매할 수 없다")
    void stockCreateFailTest() {
        // given
        final StockAmount count = StockAmount.of(10L);
        final StockMoney money = StockMoney.of(10_000_000L);

        // when & then
        assertThatThrownBy(() -> BuyStock.of(stock, count, money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid stock Total money : " + count.getAmount() * money.getMoney());
    }

    @Test
    @DisplayName("매수 금액 1000원과 수량 10개를 입력하면 총 금액은 10,000원이다.")
    void calculatedTotalMoneyTest() {
        // given
        final StockAmount count = StockAmount.of(10L);
        final StockMoney money = StockMoney.of(1000L);
        final BuyStock buyStock = BuyStock.of(stock, count, money);

        // when
        final Long calculatedTotalMoney = buyStock.getCalculatedTotalMoney();

        // then
        assertThat(calculatedTotalMoney).isEqualTo(10_000L);
    }
}
