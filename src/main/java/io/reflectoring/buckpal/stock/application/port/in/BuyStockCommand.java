package io.reflectoring.buckpal.stock.application.port.in;

import io.reflectoring.buckpal.account.domain.Account;
import io.reflectoring.buckpal.common.SelfValidating;
import io.reflectoring.buckpal.stock.domain.StockAmount;
import io.reflectoring.buckpal.stock.domain.StockMoney;
import lombok.Getter;

@Getter
public class BuyStockCommand extends SelfValidating<BuyStockCommand> {

    private final Account.AccountId accountId;
    private final Long stockId;
    private final StockAmount stockAmount;
    private final StockMoney stockMoney;

    private BuyStockCommand(Account.AccountId accountId, Long stockId, StockAmount stockAmount, StockMoney stockMoney) {
        this.accountId = accountId;
        this.stockId = stockId;
        this.stockAmount = stockAmount;
        this.stockMoney = stockMoney;
        this.validateSelf();
    }

    public static BuyStockCommand of(Account.AccountId accountId, Long stockId, Long stockAmount, Long stockMoney) {
        return new BuyStockCommand(accountId, stockId, StockAmount.of(stockAmount), StockMoney.of(stockMoney));
    }
}
