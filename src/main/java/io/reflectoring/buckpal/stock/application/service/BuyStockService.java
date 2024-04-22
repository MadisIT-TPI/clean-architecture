package io.reflectoring.buckpal.stock.application.service;

import io.reflectoring.buckpal.account.application.port.out.LoadAccountPort;
import io.reflectoring.buckpal.account.domain.Account;
import io.reflectoring.buckpal.common.UseCase;
import io.reflectoring.buckpal.stock.application.port.in.BuyStockCommand;
import io.reflectoring.buckpal.stock.application.port.in.BuyStockUseCase;
import io.reflectoring.buckpal.stock.application.port.out.CreateBuyStockPort;
import io.reflectoring.buckpal.stock.application.port.out.LoadStockPort;
import io.reflectoring.buckpal.stock.domain.BuyStock;
import io.reflectoring.buckpal.stock.domain.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;

@UseCase
@Transactional
@RequiredArgsConstructor
class BuyStockService implements BuyStockUseCase {

    private final CreateBuyStockPort createBuyStockPort;
    private final LoadStockPort loadStockPort;
    private final LoadAccountPort loadAccountPort;

    @Override
    public boolean buyStock(BuyStockCommand buyStockCommand) {
        final Stock findStock = loadStockPort.loadStock(buyStockCommand.getStockId());
        final LocalDateTime baselineDate = LocalDateTime.now().minusDays(10);
        final BuyStock buyStock = BuyStock.of(findStock, buyStockCommand.getStockAmount(), buyStockCommand.getStockMoney());
        final Account loadAccount = loadAccountPort.loadAccount(buyStockCommand.getAccountId(), baselineDate);
        final BigInteger balanceMoney = loadAccount.getBaselineBalance().getAmount();

        if (buyStock.getCalculatedTotalMoney() > balanceMoney.longValue()) {
            throw new IllegalStateException("Not enough money to buy stock");
        }
        createBuyStockPort.createBuyStock(buyStock);
        return true;
    }
}
