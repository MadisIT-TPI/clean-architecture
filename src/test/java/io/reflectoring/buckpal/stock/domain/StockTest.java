package io.reflectoring.buckpal.stock.domain;

import io.reflectoring.buckpal.account.domain.Account.AccountId;
import io.reflectoring.buckpal.account.domain.Money;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import static io.reflectoring.buckpal.common.ShareTestData.defaultCompanyShare;
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
    void 매수_가격은_10_000_000_이하여야_한다() {
        // given
        Stock stock = defaultStock()
                .withStockPrice(StockPrice.of(List.of(
                        Fluctuation.of(Money.of(1_000_000), LocalDateTime.now())
                )))
                .build();

        // when and then
        Exception e = assertThrows(IllegalStateException.class,
                () -> stock.getPrice(11));
        assertEquals("Stock price must be less than 10_000_000", e.getMessage());
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
    void 주식을_매수할_때_수량은_1_이상_10_이하여야_한다__0() {
        // given
        Stock stock = defaultStock().build();

        // when and then
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> stock.buyShares(0, new AccountId(3L)));
        assertEquals("Can only buy between 1 and 10 shares", e.getMessage());
    }

    @Test
    void 주식을_매수할_때_수량은_1_이상_10_이하여야_한다__11() {
        // given
        Stock stock = defaultStock().build();

        // when and then
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> stock.buyShares(11, new AccountId(3L)));
        assertEquals("Can only buy between 1 and 10 shares", e.getMessage());
    }

    @Test
    void 주식을_매수할_때_매수_가능한_수량을_넘을_수_없다() {
        // given
        Stock stock = defaultStock()
                .withShares(List.of(
                        defaultCompanyShare()
                                .withQuantity(1)
                                .build()))
                .build();

        // when and then
        Exception e = assertThrows(IllegalStateException.class,
                () -> stock.buyShares(10, new AccountId(3L)));
        assertEquals("Not enough shares available", e.getMessage());
    }
}