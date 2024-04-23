package io.reflectoring.buckpal.stock.application.service;

import io.reflectoring.buckpal.account.application.port.out.AccountLock;
import io.reflectoring.buckpal.account.application.port.out.LoadAccountPort;
import io.reflectoring.buckpal.account.application.port.out.UpdateAccountStatePort;
import io.reflectoring.buckpal.account.application.service.MoneyTransferProperties;
import io.reflectoring.buckpal.account.application.service.ThresholdExceededException;
import io.reflectoring.buckpal.account.domain.Account;
import io.reflectoring.buckpal.account.domain.Money;
import io.reflectoring.buckpal.common.UseCase;
import io.reflectoring.buckpal.stock.application.port.in.BuyStockCommand;
import io.reflectoring.buckpal.stock.application.port.in.BuyStockUseCase;
import io.reflectoring.buckpal.stock.application.port.out.LoadCompanyPort;
import io.reflectoring.buckpal.stock.application.port.out.UpdateShareInAccountOrAvailablePort;
import io.reflectoring.buckpal.stock.domain.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@UseCase
@Transactional
public class BuyStockService implements BuyStockUseCase {

    private final LoadAccountPort loadAccountPort;
    private final AccountLock accountLock;
    private final LoadCompanyPort loadCompanyPort;
    private final UpdateAccountStatePort updateAccountStatePort;
    private final MoneyTransferProperties moneyTransferProperties;
    private final UpdateShareInAccountOrAvailablePort updateShareInAccountOrAvailablePort;

    @Override
    public boolean buyStock(BuyStockCommand command) {
        LocalDateTime baselineDate = LocalDateTime.now().minusDays(10);

        Account sourceAccount = loadAccountPort.loadAccount(
                command.getSourceAccountId(),
                baselineDate);

        Company targetCompany = loadCompanyPort.loadCompany(command.getTargetCompanyId());

        Account targetAccount = loadAccountPort.loadAccount(
                targetCompany.getOwnerAccountId(),
                baselineDate);

        Account.AccountId sourceAccountId = sourceAccount.getId()
                .orElseThrow(() -> new IllegalStateException("expected source account ID not to be empty"));
        Account.AccountId targetAccountId = targetAccount.getId()
                .orElseThrow(() -> new IllegalStateException("expected target account ID not to be empty"));

        Money stockPrice = targetCompany.getStock().getPrice(command.getAmount());

        checkThreshold(stockPrice);

        accountLock.lockAccount(sourceAccountId);
        if (!sourceAccount.withdraw(stockPrice, targetAccountId)) {
            accountLock.releaseAccount(sourceAccountId);
            return false;
        }

        accountLock.lockAccount(targetAccountId);
        if (!targetAccount.deposit(stockPrice, sourceAccountId)) {
            accountLock.releaseAccount(sourceAccountId);
            accountLock.releaseAccount(targetAccountId);
            return false;
        }

        updateAccountStatePort.updateActivities(sourceAccount);
        updateAccountStatePort.updateActivities(targetAccount);

        accountLock.releaseAccount(sourceAccountId);
        accountLock.releaseAccount(targetAccountId);

        targetCompany.getStock().buyShares(command.getAmount(), sourceAccountId);
        updateShareInAccountOrAvailablePort.updateShareInAccountOrAvailable(targetCompany.getStock(), sourceAccountId);

        return true;
    }

    private void checkThreshold(Money stockPrice) {
        if (stockPrice.isGreaterThan(moneyTransferProperties.getMaximumBuyStockThreshold())) {
            throw new ThresholdExceededException(moneyTransferProperties.getMaximumBuyStockThreshold(), stockPrice);
        }
    }
}
