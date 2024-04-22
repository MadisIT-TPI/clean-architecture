package io.reflectoring.buckpal.stock.adapter.out.persistence;

import io.reflectoring.buckpal.stock.domain.BuyStock;
import io.reflectoring.buckpal.stock.domain.Stock;
import io.reflectoring.buckpal.stock.domain.StockAmount;
import io.reflectoring.buckpal.stock.domain.StockMoney;
import org.springframework.stereotype.Component;

@Component
class StockMapper {

    Stock mapToDomainEntity(StockJpaEntity stock) {
        return Stock.of(stock.getId());
    }

    BuyStockJpaEntity mapToJpaEntity(BuyStock buyStock) {
        final StockAmount stockAmount = buyStock.getCount();
        final StockMoney stockMoney = buyStock.getMoney();
        return new BuyStockJpaEntity(buyStock.getStock().getId(), stockMoney.getMoney(), stockAmount.getAmount());
    }

    BuyStock mapToDomainEntity(BuyStockJpaEntity buyStockJpaEntity) {
        final Stock stock = Stock.of(buyStockJpaEntity.getStockId());
        final StockAmount stockAmount = StockAmount.of(buyStockJpaEntity.getAmount());
        final StockMoney stockMoney = StockMoney.of(buyStockJpaEntity.getMoney());
        return BuyStock.of(stock, stockAmount, stockMoney);
    }
}
