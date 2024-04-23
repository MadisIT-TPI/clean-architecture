package io.reflectoring.buckpal.stock.domain;

import io.reflectoring.buckpal.account.domain.Account.AccountId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.Min;

// 한 주를 의미
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Share {

    @Getter
    ShareId id;

    AccountId ownerAccountId;

    @Min(0)
    @Getter
    Integer amount;

    public static Share withId(ShareId shareId, AccountId ownerAccountId, Integer amount) {
        return new Share(shareId, ownerAccountId, amount);
    }

    public static Share withoutId(AccountId ownerAccountId, Integer amount) {
        return new Share(null, ownerAccountId, amount);
    }

    public boolean isAvailableToBuy() {
        return ownerAccountId == null;
    }

    public void bought(Integer amount) {
        if (this.amount < amount) {
            throw new IllegalStateException("Not enough shares available");
        }

        this.amount -= amount;
    }

    public void given(Integer amount) {
        this.amount += amount;
    }

    public boolean isOwnedBy(@NonNull AccountId accountId) {
        return accountId.equals(ownerAccountId);
    }

    @Value
    public static class ShareId {
        Long value;
    }
}
