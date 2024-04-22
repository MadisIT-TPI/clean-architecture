package io.reflectoring.buckpal.stock.application.port.in;

public interface BuyStockUseCase {

    boolean buyStock(BuyStockCommand buyStockCommand);
}
