package io.reflectoring.buckpal.stock.domain;

import io.reflectoring.buckpal.account.domain.Account.AccountId;
import io.reflectoring.buckpal.account.domain.Money;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import static io.reflectoring.buckpal.common.StockTestData.defaultStock;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StockTest {

    @Test
    void 가격을_반환한다() {
        // given
        Stock stock = defaultStock().build();

        // when
        Money price = stock.getPrice(10);

        // then
        assertEquals(BigInteger.valueOf(500), price.getAmount());
    }

    @Test
    void 가격은_0_이상_이어야_한다() {
        // given
        Stock stock = defaultStock()
                .withStockPrice(StockPrice.of(List.of(
                        Fluctuation.of(Money.of(-20), LocalDateTime.now())
                )))
                .build();

        // when and then
        Exception e = assertThrows(IllegalStateException.class,
                () -> stock.getPrice(10));
        assertEquals("Stock price must be positive", e.getMessage());
    }

    @Test
    void 주식을_매수한다__뉴비() {
        // given
        Stock stock = defaultStock().build();

        // when
        stock.buyShares(10, new AccountId(3L));

        // then
        assertAll(
                () -> assertEquals(10, stock.getShare(new AccountId(3L)).get().getQuantity()),
                () -> assertEquals(990, stock.availableShare().getQuantity())
        );
    }

    @Test
    void 주식을_매수한다__올드비() {
        // given
        Stock stock = defaultStock().build();

        // when
        stock.buyShares(10, new AccountId(342L));

        // then
        assertAll(
                () -> assertEquals(110, stock.getShare(new AccountId(342L)).get().getQuantity()),
                () -> assertEquals(990, stock.availableShare().getQuantity())
        );
    }

    @Test
    void 주식을_매수할_때_매수_가능한_수량을_넘을_수_없다() {
        // given
        Stock stock = defaultStock().build();

        // when and then
        Exception e = assertThrows(IllegalStateException.class,
                () -> stock.buyShares(1001, new AccountId(3L)));
        assertEquals("Not enough shares available", e.getMessage());
    }
}