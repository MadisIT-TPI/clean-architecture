package io.reflectoring.buckpal.stock.domain;

import io.reflectoring.buckpal.account.domain.Account;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

// 한 주를 의미
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Share {

    @Getter
    ShareId id;

    Account.AccountId ownerAccountId;

    public boolean isAvailableToBuy() {
        return ownerAccountId == null;
    }

    public void bought(Account.AccountId buyerAccountId) {
        this.ownerAccountId = buyerAccountId;
    }

    @Value
    public static class ShareId {
        Long value;
    }
}
