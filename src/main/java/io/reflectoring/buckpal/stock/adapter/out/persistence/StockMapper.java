package io.reflectoring.buckpal.stock.adapter.out.persistence;

import io.reflectoring.buckpal.account.domain.Account;
import io.reflectoring.buckpal.account.domain.Money;
import io.reflectoring.buckpal.stock.domain.Fluctuation;
import io.reflectoring.buckpal.stock.domain.Share;
import io.reflectoring.buckpal.stock.domain.Share.ShareId;
import io.reflectoring.buckpal.stock.domain.Stock;
import io.reflectoring.buckpal.stock.domain.Stock.StockId;
import io.reflectoring.buckpal.stock.domain.StockPrice;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockMapper {

    Stock mapToDomainEntity(
            StockJpaEntity stock
    ) {
        List<Fluctuation> fluctuations = stock.getFluctuations().stream()
                .map(fluctuation -> Fluctuation.of(
                        Money.of(fluctuation.getAmount()),
                        fluctuation.getTimestamp()
                ))
                .collect(Collectors.toList());

        List<Share> shares = stock.getShares().stream()
                .map(share -> Share.withId(
                        new ShareId(share.getId()),
                        new Account.AccountId(share.getOwnerAccountId()),
                        share.getQuantity()
                ))
                .collect(Collectors.toList());

        return Stock.withId(
                new StockId(stock.getId()),
                StockPrice.of(fluctuations),
                shares
        );
    }

    public ShareJpaEntity mapToJpaEntity(Share share, Stock stock) {
        return new ShareJpaEntity(
                share.getId() == null ? null : share.getId().getValue(),
                share.getOwnerAccountId().getValue(),
                share.getQuantity(),
                stock.getId().getValue()
        );
    }
}
