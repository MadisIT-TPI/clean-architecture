package io.reflectoring.buckpal.common;

import io.reflectoring.buckpal.account.domain.Account.AccountId;
import io.reflectoring.buckpal.account.domain.Money;
import io.reflectoring.buckpal.stock.domain.Fluctuation;
import io.reflectoring.buckpal.stock.domain.Share;
import io.reflectoring.buckpal.stock.domain.Stock;
import io.reflectoring.buckpal.stock.domain.Stock.StockId;
import io.reflectoring.buckpal.stock.domain.StockPrice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static io.reflectoring.buckpal.common.ShareTestData.defaultCompanyShare;
import static io.reflectoring.buckpal.common.ShareTestData.defaultShare;

public class StockTestData {

    public static StockBuilder defaultStock() {
        return new StockBuilder()
                .withStockId(new StockId(31L))
                .withStockPrice(StockPrice.of(List.of(
                        Fluctuation.of(Money.of(1), LocalDateTime.of(2000, 1, 1, 0, 0)),
                        Fluctuation.of(Money.of(100), LocalDateTime.of(2000, 1, 2, 0, 0)),
                        Fluctuation.of(Money.of(50), LocalDateTime.of(2000, 1, 3, 0, 0))
                )))
                .withShares(new ArrayList<>(List.of(
                        defaultCompanyShare(),
                        defaultShare()
                )))
                .withBaseAccountId(new AccountId(415L));
    }

    public static class StockBuilder {

        private StockId stockId;
        private StockPrice stockPrice;
        private List<Share> shares;
        private AccountId baseAccountId;

        public StockBuilder withStockId(StockId stockId) {
            this.stockId = stockId;
            return this;
        }

        public StockBuilder withStockPrice(StockPrice stockPrice) {
            this.stockPrice = stockPrice;
            return this;
        }

        public StockBuilder withShares(List<Share> shares) {
            this.shares = shares;
            return this;
        }

        public StockBuilder withBaseAccountId(AccountId baseAccountId) {
            this.baseAccountId = baseAccountId;
            return this;
        }

        public Stock build() {
            return Stock.withId(stockId, stockPrice, shares, baseAccountId);
        }

    }
}
