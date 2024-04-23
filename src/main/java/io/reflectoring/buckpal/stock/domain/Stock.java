package io.reflectoring.buckpal.stock.domain;

import io.reflectoring.buckpal.account.domain.Account;
import io.reflectoring.buckpal.account.domain.Money;
import io.reflectoring.buckpal.stock.domain.Share.ShareId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Stock {

    private final StockId id;

    private StockPrice stockPrice;

    private List<Share> shares;

    @Value
    public static class StockId {
        Long value;
    }

    public Money getPrice(Integer amount) {
        Money price = stockPrice.getCurrentPrice().multiply(amount);

        if (price.isNegative()) {
            throw new IllegalStateException("Price must be positive");
        }

        return price;
    }

    public void buyShares(Integer amount, Account.AccountId buyerAccountId) {
        Share availableShare = availableShare();

        availableShare.bought(amount);
        Share accountsShare = getShare(buyerAccountId)
                .orElseGet(() -> Share.withoutId(buyerAccountId, 0));
        accountsShare.given(amount);
    }

    private Optional<Share> getShare(Account.AccountId accountId) {
        return shares.stream()
                .filter(share -> share.isOwnedBy(accountId))
                .findFirst();
    }

    private Share availableShare() {
        return shares.stream()
                .filter(Share::isAvailableToBuy)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No shares available"));
    }
}
