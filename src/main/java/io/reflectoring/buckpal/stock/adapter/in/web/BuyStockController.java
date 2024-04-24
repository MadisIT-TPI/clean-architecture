package io.reflectoring.buckpal.stock.adapter.in.web;

import io.reflectoring.buckpal.account.domain.Account.AccountId;
import io.reflectoring.buckpal.common.WebAdapter;
import io.reflectoring.buckpal.stock.application.port.in.BuyStockCommand;
import io.reflectoring.buckpal.stock.application.port.in.BuyStockUseCase;
import io.reflectoring.buckpal.stock.domain.Stock.StockId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class BuyStockController {

    private final BuyStockUseCase buyStockUseCase;

    @PostMapping("/stocks/buy/{accountId}/{stockId}/{amount}")
    public boolean buyStock(
            @PathVariable("accountId") Long accountId,
            @PathVariable("stockId") Long stockId,
            @PathVariable("amount") Integer amount) {

        BuyStockCommand command = new BuyStockCommand(
                new AccountId(accountId),
                new StockId(stockId),
                amount
        );

        return buyStockUseCase.buyStock(command);
    }
}
