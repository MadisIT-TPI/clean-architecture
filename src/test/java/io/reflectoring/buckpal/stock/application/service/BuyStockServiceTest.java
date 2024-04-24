package io.reflectoring.buckpal.stock.application.service;

import io.reflectoring.buckpal.account.application.port.out.AccountLock;
import io.reflectoring.buckpal.account.application.port.out.LoadAccountPort;
import io.reflectoring.buckpal.account.application.service.MoneyTransferProperties;
import io.reflectoring.buckpal.account.domain.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BuyStockServiceTest {

    @InjectMocks
    private BuyStockService buyStockService;

    @Mock
    private LoadAccountPort loadAccountPort;

    @Mock
    private AccountLock loadStockPort;

    @Test
    void 주식을_매수한다() {
        // given
        // when
        // then
    }
}