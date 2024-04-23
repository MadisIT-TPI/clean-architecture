package io.reflectoring.buckpal.stock.adapter.in.web;

import io.reflectoring.buckpal.account.domain.Account;
import io.reflectoring.buckpal.common.WebAdapter;
import io.reflectoring.buckpal.stock.application.port.in.BuyStockCommand;
import io.reflectoring.buckpal.stock.application.port.in.BuyStockUseCase;
import io.reflectoring.buckpal.stock.domain.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class BuyStockController {

    private final BuyStockUseCase buyStockUseCase;

    @PostMapping("/stocks/buy/{accountId}/{companyId}/{amount}")
    public boolean buyStock(
            @PathVariable("accountId") Long accountId,
            @PathVariable("companyId") Long companyId,
            @PathVariable("amount") Integer amount) {

        BuyStockCommand command = new BuyStockCommand(
                new Account.AccountId(accountId),
                new Company.CompanyId(companyId),
                amount
        );

        return buyStockUseCase.buyStock(command);
    }
}