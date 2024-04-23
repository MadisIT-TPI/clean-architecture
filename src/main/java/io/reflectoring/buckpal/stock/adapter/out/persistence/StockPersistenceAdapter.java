package io.reflectoring.buckpal.stock.adapter.out.persistence;

import io.reflectoring.buckpal.account.domain.Account.AccountId;
import io.reflectoring.buckpal.common.PersistenceAdapter;
import io.reflectoring.buckpal.stock.application.port.out.UpdateShareInAccountOrAvailablePort;
import io.reflectoring.buckpal.stock.domain.Stock;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class StockPersistenceAdapter implements UpdateShareInAccountOrAvailablePort {

    @Override
    public void updateShareInAccountOrAvailable(Stock stock, AccountId account) {
        return;
    }
}
