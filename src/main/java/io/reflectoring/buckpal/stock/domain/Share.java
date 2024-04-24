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

    @Getter
    AccountId ownerAccountId;

    @Min(0)
    @Getter
    Integer quantity;

    public static Share withId(ShareId shareId, AccountId ownerAccountId, Integer quantity) {
        return new Share(shareId, ownerAccountId, quantity);
    }

    public static Share withoutId(AccountId ownerAccountId, Integer quantity) {
        return new Share(null, ownerAccountId, quantity);
    }

    public boolean isAvailableToBuy() {
        return ownerAccountId == null;
    }

    public void bought(Integer quantity) {
        if (this.quantity < quantity) {
            throw new IllegalStateException("Not enough shares available");
        }

        this.quantity -= quantity;
    }

    public void given(Integer quantity) {
        this.quantity += quantity;
    }

    public boolean isOwnedBy(@NonNull AccountId accountId) {
        return accountId.equals(ownerAccountId);
    }

    @Value
    public static class ShareId {
        Long value;
    }
}
