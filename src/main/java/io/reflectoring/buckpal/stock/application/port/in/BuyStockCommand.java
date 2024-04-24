package io.reflectoring.buckpal.stock.application.port.in;

import io.reflectoring.buckpal.account.domain.Account.AccountId;
import io.reflectoring.buckpal.common.SelfValidating;
import io.reflectoring.buckpal.stock.domain.Stock.StockId;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class BuyStockCommand extends SelfValidating<BuyStockCommand> {

    @NotNull
    AccountId sourceAccountId;

    @NotNull
    StockId targetStockId;

    @Max(10)
    @Min(0)
    @NotNull
    Integer quantity;

    public BuyStockCommand(
            AccountId sourceAccountId,
            StockId targetStockId,
            Integer quantity
    ) {
        this.sourceAccountId = sourceAccountId;
        this.quantity = quantity;
        this.targetStockId = targetStockId;
        this.validateSelf();
    }
}
