package io.reflectoring.buckpal.stock.adapter.out.persistence;

import io.reflectoring.buckpal.common.PersistenceAdapter;
import io.reflectoring.buckpal.stock.application.port.out.CreateBuyStockPort;
import io.reflectoring.buckpal.stock.domain.BuyStock;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
class BuyStockPersistenceAdapter implements CreateBuyStockPort {

    private final StockMapper stockMapper;
    private final BuyStockRepository buyStockRepository;

    @Override
    public BuyStock createBuyStock(BuyStock buyStock) {
        final BuyStockJpaEntity buyStockJpaEntity = stockMapper.mapToJpaEntity(buyStock);
        final BuyStockJpaEntity savedBuyStock = buyStockRepository.save(buyStockJpaEntity);

        return stockMapper.mapToDomainEntity(savedBuyStock);
    }
}
