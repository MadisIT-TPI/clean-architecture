package io.reflectoring.buckpal.stock.application.port.out;

import io.reflectoring.buckpal.stock.domain.BuyStock;

public interface CreateBuyStockPort {
    BuyStock createBuyStock(BuyStock buyStock);
}
