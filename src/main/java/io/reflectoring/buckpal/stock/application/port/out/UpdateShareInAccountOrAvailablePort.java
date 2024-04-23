package io.reflectoring.buckpal.stock.application.port.out;

import io.reflectoring.buckpal.account.domain.Account.AccountId;
import io.reflectoring.buckpal.stock.domain.Stock;

public interface UpdateShareInAccountOrAvailablePort {

	void updateShareInAccountOrAvailable(Stock stock, AccountId account);
}
