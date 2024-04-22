package io.reflectoring.buckpal.stock.application.port.in;

import io.reflectoring.buckpal.stock.domain.StockAmount;
import io.reflectoring.buckpal.stock.domain.StockMoney;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BuyStockCommand {

    private final StockAmount stockAmount;
    private final StockMoney stockMoney;

    public static BuyStockCommand of(StockAmount stockAmount, StockMoney stockMoney) {
        return new BuyStockCommand(stockAmount, stockMoney);
    }
}
