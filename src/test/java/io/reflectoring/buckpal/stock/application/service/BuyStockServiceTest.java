package io.reflectoring.buckpal.stock.application.service;

import io.reflectoring.buckpal.account.application.port.out.LoadAccountPort;
import io.reflectoring.buckpal.account.domain.Account;
import io.reflectoring.buckpal.account.domain.Money;
import io.reflectoring.buckpal.stock.application.port.in.BuyStockCommand;
import io.reflectoring.buckpal.stock.application.port.out.CreateBuyStockPort;
import io.reflectoring.buckpal.stock.application.port.out.LoadStockPort;
import io.reflectoring.buckpal.stock.domain.BuyStock;
import io.reflectoring.buckpal.stock.domain.Stock;
import io.reflectoring.buckpal.stock.domain.StockAmount;
import io.reflectoring.buckpal.stock.domain.StockMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class BuyStockServiceTest {

    @InjectMocks
    private BuyStockService buyStockService;
    @Mock
    private CreateBuyStockPort createBuyStockPort;
    @Mock
    private LoadStockPort loadStockPort;
    @Mock
    private LoadAccountPort loadAccountPort;

    @Test
    @DisplayName("주식 매수 정보가 주어졌을때 주식을 매수한다.")
    void buyStockTest() {
        // given
        final Long amount = 10L;
        final Long money = 1000L;
        final Money baselineBalance = Money.of(10_000L);
        final BuyStockCommand buyStockCommand = BuyStockCommand.of(new Account.AccountId(1L), 1L, amount, money);
        given(loadStockPort.loadStock(eq(buyStockCommand.getStockId()))).willReturn(Stock.of(1L));
        given(loadAccountPort.loadAccount(eq(buyStockCommand.getAccountId()), any(LocalDateTime.class))).willReturn(Account.withId(buyStockCommand.getAccountId(), baselineBalance, null));
        given(createBuyStockPort.createBuyStock(any(BuyStock.class))).willReturn(BuyStock.of(Stock.of(1L), StockAmount.of(amount), StockMoney.of(money)));

        // when
        final boolean result = buyStockService.buyStock(buyStockCommand);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("주식 매수 금액이 잔액보다 많을때 예외를 발생시킨다.")
    void buyStockFailTest() {
        // given
        final Long amount = 10L;
        final Long money = 10_000L;
        final Money baselineBalance = Money.of(10_000L);
        final BuyStockCommand buyStockCommand = BuyStockCommand.of(new Account.AccountId(1L), 1L, amount, money);
        given(loadStockPort.loadStock(eq(buyStockCommand.getStockId()))).willReturn(Stock.of(1L));
        given(loadAccountPort.loadAccount(eq(buyStockCommand.getAccountId()), any(LocalDateTime.class))).willReturn(Account.withId(buyStockCommand.getAccountId(), baselineBalance, null));

        // when & then
        assertThatThrownBy(() -> buyStockService.buyStock(buyStockCommand))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Not enough money to buy stock");
    }

}
