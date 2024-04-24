package io.reflectoring.buckpal.stock.adapter.out.persistence;

import io.reflectoring.buckpal.account.domain.Account.AccountId;
import io.reflectoring.buckpal.common.PersistenceAdapter;
import io.reflectoring.buckpal.stock.application.port.out.LoadStockPort;
import io.reflectoring.buckpal.stock.application.port.out.UpdateShareInAccountOrAvailablePort;
import io.reflectoring.buckpal.stock.domain.Share;
import io.reflectoring.buckpal.stock.domain.Stock;
import io.reflectoring.buckpal.stock.domain.Stock.StockId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class StockPersistenceAdapter implements LoadStockPort, UpdateShareInAccountOrAvailablePort {

    private final StockRepository stockRepository;
    private final ShareRepository shareRepository;
    private final StockMapper stockMapper;

    @Override
    public Stock loadStock(StockId stockId) {
        StockJpaEntity stock = stockRepository.findByCompanyId(stockId.getValue())
                .orElseThrow();

        return stockMapper.mapToDomainEntity(stock);
    }

    @Override
    public void updateShareInAccountOrAvailable(Stock stock, AccountId account) {
        for (Share share : stock.getShares()) {
            if (share.getId() == null || share.getOwnerAccountId().equals(account)) {
                shareRepository.save(stockMapper.mapToJpaEntity(share, stock));
            }
        }
    }
}
