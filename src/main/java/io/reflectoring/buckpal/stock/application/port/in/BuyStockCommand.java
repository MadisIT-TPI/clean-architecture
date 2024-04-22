package io.reflectoring.buckpal.stock.application.port.in;

import io.reflectoring.buckpal.account.domain.Account;
import io.reflectoring.buckpal.stock.domain.StockAmount;
import io.reflectoring.buckpal.stock.domain.StockMoney;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BuyStockCommand {

    private final Account.AccountId accountId;
    private final Long stockId;
    private final StockAmount stockAmount;
    private final StockMoney stockMoney;

    public static BuyStockCommand of(Account.AccountId accountId, Long stockId, Long stockAmount, Long stockMoney) {
        return new BuyStockCommand(accountId, stockId, StockAmount.of(stockAmount), StockMoney.of(stockMoney));
    }
}
