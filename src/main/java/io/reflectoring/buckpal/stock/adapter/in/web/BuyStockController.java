package io.reflectoring.buckpal.stock.adapter.in.web;

import io.reflectoring.buckpal.account.domain.Account;
import io.reflectoring.buckpal.common.WebAdapter;
import io.reflectoring.buckpal.stock.application.port.in.BuyStockCommand;
import io.reflectoring.buckpal.stock.application.port.in.BuyStockUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
class BuyStockController {
    private final BuyStockUseCase buyStockUseCase;

    @PostMapping(path = "/stock/{stockId}")
    void buyStock(@PathVariable("stockId") Long stockId, @RequestBody BuyStockRequest.Create request) {
        final BuyStockCommand command = BuyStockCommand.of(new Account.AccountId(request.getAccountId()), stockId, request.getAmount(), request.getMoney());
        buyStockUseCase.buyStock(command);
    }
}
