package io.reflectoring.buckpal.stock.application.service;

import io.reflectoring.buckpal.account.application.port.out.AccountLock;
import io.reflectoring.buckpal.account.application.port.out.LoadAccountPort;
import io.reflectoring.buckpal.account.application.port.out.UpdateAccountStatePort;
import io.reflectoring.buckpal.account.application.service.MoneyTransferProperties;
import io.reflectoring.buckpal.account.domain.Account;
import io.reflectoring.buckpal.account.domain.Account.AccountId;
import io.reflectoring.buckpal.account.domain.Money;
import io.reflectoring.buckpal.stock.application.port.in.BuyStockCommand;
import io.reflectoring.buckpal.stock.application.port.out.LoadStockPort;
import io.reflectoring.buckpal.stock.application.port.out.UpdateShareInAccountOrAvailablePort;
import io.reflectoring.buckpal.stock.domain.Stock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static io.reflectoring.buckpal.common.AccountTestData.defaultAccount;
import static io.reflectoring.buckpal.common.StockTestData.defaultStock;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BuyStockServiceTest {

    @InjectMocks
    private BuyStockService buyStockService;

    @Mock
    private LoadAccountPort loadAccountPort;

    @Mock
    private AccountLock accountLock;

    @Mock
    private LoadStockPort loadStockPort;

    @Mock
    private UpdateAccountStatePort updateAccountStatePort;

    @Mock
    private UpdateShareInAccountOrAvailablePort updateShareInAccountOrAvailablePort;

    @Test
    void 주식을_매수한다() {
        // given
        Account sourceAccount = defaultAccount()
                .withAccountId(new AccountId(1L))
                .build();

        given(loadAccountPort.loadAccount(eq(sourceAccount.getId().get()), any(LocalDateTime.class)))
                .willReturn(sourceAccount);

        Account targetAccount = defaultAccount()
                .withAccountId(new AccountId(3L))
                .build();

        Stock stock = defaultStock()
                .withBaseAccountId(targetAccount.getId().get())
                .build();

        given(loadStockPort.loadStock(eq(stock.getId())))
                .willReturn(stock);

        given(loadAccountPort.loadAccount(eq(targetAccount.getId().get()), any(LocalDateTime.class)))
                .willReturn(targetAccount);

        willDoNothing().given(updateAccountStatePort).updateActivities(any(Account.class));
        willDoNothing().given(updateShareInAccountOrAvailablePort).updateShareInAccountOrAvailable(stock, sourceAccount.getId().get());
        
        // when
        buyStockService.buyStock(new BuyStockCommand(sourceAccount.getId().get(), stock.getId(), 10));

        // then
        assertAll(
                () -> assertEquals(10, stock.getShare(sourceAccount.getId().get()).get().getQuantity()),
                () -> assertEquals(990, stock.availableShare().getQuantity()),
                () -> assertEquals(3, sourceAccount.getActivityWindow().getActivities().size()),
                () -> assertEquals(3, targetAccount.getActivityWindow().getActivities().size())
        );
        verify(accountLock, times(1)).lockAccount(sourceAccount.getId().get());
        verify(accountLock, times(1)).lockAccount(targetAccount.getId().get());
        verify(accountLock, times(1)).releaseAccount(sourceAccount.getId().get());
        verify(accountLock, times(1)).releaseAccount(targetAccount.getId().get());
        verify(updateAccountStatePort, times(2)).updateActivities(any(Account.class));
        verify(updateShareInAccountOrAvailablePort, times(1)).updateShareInAccountOrAvailable(stock, sourceAccount.getId().get());
    }
}