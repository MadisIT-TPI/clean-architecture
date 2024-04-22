package io.reflectoring.buckpal.stock.application.port.out;

import io.reflectoring.buckpal.account.domain.Account.AccountId;
import io.reflectoring.buckpal.stock.domain.Share.ShareId;

import java.util.List;

public interface UpdateShareOwnerAccountPort {

	void updateShareOwnerAccount(List<ShareId> ids, AccountId account);
}
