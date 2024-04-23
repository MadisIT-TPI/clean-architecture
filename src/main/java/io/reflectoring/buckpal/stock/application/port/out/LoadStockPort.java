package io.reflectoring.buckpal.stock.application.port.out;

import io.reflectoring.buckpal.stock.domain.Stock;

public interface LoadStockPort {

    Stock loadStock(Long stockId);
}
