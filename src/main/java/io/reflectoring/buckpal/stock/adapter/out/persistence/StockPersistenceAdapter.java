package io.reflectoring.buckpal.stock.adapter.out.persistence;

import io.reflectoring.buckpal.common.PersistenceAdapter;
import io.reflectoring.buckpal.stock.application.port.out.LoadStockPort;
import io.reflectoring.buckpal.stock.domain.Stock;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;

@PersistenceAdapter
@RequiredArgsConstructor
class StockPersistenceAdapter implements LoadStockPort {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    @Override
    public Stock loadStock(Long stockId) {
        final StockJpaEntity stockJpaEntity = stockRepository.findById(stockId).orElseThrow(EntityNotFoundException::new);
        return stockMapper.mapToDomainEntity(stockJpaEntity);
    }
}
