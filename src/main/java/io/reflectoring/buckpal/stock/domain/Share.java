package io.reflectoring.buckpal.stock.domain;

import io.reflectoring.buckpal.account.domain.Account;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

// 한 주를 의미
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Share {

    Account.AccountId ownerAccountId;
}
