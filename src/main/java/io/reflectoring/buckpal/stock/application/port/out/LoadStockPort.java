package io.reflectoring.buckpal.stock.application.port.out;

import io.reflectoring.buckpal.stock.domain.Stock;
import io.reflectoring.buckpal.stock.domain.Stock.StockId;

public interface LoadStockPort {

    Stock loadStock(StockId stockId);
}
