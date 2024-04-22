package io.reflectoring.buckpal.stock.domain;

import io.reflectoring.buckpal.account.domain.Account;
import io.reflectoring.buckpal.account.domain.Money;
import io.reflectoring.buckpal.stock.domain.Share.ShareId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return stockPrice.getCurrentPrice().multiply(amount);
    }

    public List<ShareId> buyShares(Integer amount, Account.AccountId buyerAccountId) {
        List<Share> availableShares = availableShares();
        List<ShareId> boughtShareIds = new ArrayList<>();

        if (amount > availableShares.size()) {
            throw new IllegalStateException("Not enough shares available");
        }

        for (int i = 0; i < amount; i++) {
            Share share = availableShares.get(i);
            share.bought(buyerAccountId);
            boughtShareIds.add(share.getId());
        }

        return boughtShareIds;
    }

    private List<Share> availableShares() {
        return shares.stream()
                .filter(Share::isAvailableToBuy)
                .collect(Collectors.toList());
    }
}
