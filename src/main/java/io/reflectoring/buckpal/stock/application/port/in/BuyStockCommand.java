package io.reflectoring.buckpal.stock.application.port.in;

import io.reflectoring.buckpal.account.domain.Account.AccountId;
import io.reflectoring.buckpal.common.SelfValidating;
import io.reflectoring.buckpal.stock.domain.Company.CompanyId;
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

    @Max(10)
    @Min(0)
    @NotNull
    Integer amount;

    @NotNull
    CompanyId targetCompanyId;

    public BuyStockCommand(
            AccountId sourceAccountId,
            Integer amount,
            CompanyId targetCompanyId
    ) {
        this.sourceAccountId = sourceAccountId;
        this.amount = amount;
        this.targetCompanyId = targetCompanyId;
        this.validateSelf();
    }
}
