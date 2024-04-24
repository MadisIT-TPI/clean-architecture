package io.reflectoring.buckpal.stock.domain;

import io.reflectoring.buckpal.account.domain.Account.AccountId;
import io.reflectoring.buckpal.account.domain.Money;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Stock {

    @Getter
    private final StockId id;

    private StockPrice stockPrice;

    @Getter
    private List<Share> shares;

    public static Stock withId(StockId stockId, StockPrice stockPrice, List<Share> shares) {
        return new Stock(stockId, stockPrice, shares);
    }

    @Value
    public static class StockId {
        Long value;
    }

    public Money getPrice(Integer amount) {
        Money price = stockPrice.getCurrentPrice().multiply(amount);

        if (price.isNegative()) {
            throw new IllegalStateException("Stock price must be positive");
        }

        return price;
    }

    public void buyShares(Integer amount, AccountId buyerAccountId) {
        Share availableShare = availableShare();

        availableShare.bought(amount);
        Share accountsShare = getShare(buyerAccountId)
                .orElseGet(() -> addShare(buyerAccountId, 0));
        accountsShare.given(amount);
    }

    public Optional<Share> getShare(AccountId accountId) {
        // todo - question - test 를 위해 public?
        return shares.stream()
                .filter(share -> share.isOwnedBy(accountId))
                .findFirst();
    }

    private Share addShare(AccountId buyerAccountId, Integer amount) {
        Share share = Share.withoutId(buyerAccountId, amount);
        shares.add(share);
        return share;
    }

    public Share availableShare() {
        // todo - question - test 를 위해 public?
        return shares.stream()
                .filter(Share::isAvailableToBuy)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No shares available"));
    }
}
