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

    @Getter
    private AccountId baseAccountId;

    public static Stock withId(StockId stockId, StockPrice stockPrice, List<Share> shares, AccountId baseAccountId) {
        return new Stock(stockId, stockPrice, shares, baseAccountId);
    }

    @Value
    public static class StockId {
        Long value;
    }

    public Money getPrice(Integer quantity) {
        Money price = stockPrice.getCurrentPrice().multiply(quantity);

        if (price.isNegative()) {
            throw new IllegalStateException("Stock price must be positive");
        }

        if (price.isGreaterThan(Money.of(10_000_000))) {
            throw new IllegalStateException("Stock price must be less than 10_000_000");
        }

        return price;
    }

    public void buyShares(Integer quantity, AccountId buyerAccountId) {
        if (!(1 <= quantity && quantity <= 10)) {
            throw new IllegalArgumentException("Can only buy between 1 and 10 shares");

        }

        Share availableShare = availableShare();

        availableShare.bought(quantity);
        Share accountsShare = getShare(buyerAccountId)
                .orElseGet(() -> addShare(buyerAccountId, 0));
        accountsShare.given(quantity);
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
