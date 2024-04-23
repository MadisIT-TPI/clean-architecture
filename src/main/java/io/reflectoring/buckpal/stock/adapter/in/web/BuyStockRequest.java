package io.reflectoring.buckpal.stock.adapter.in.web;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class BuyStockRequest {

    @Getter
    @NoArgsConstructor
    public static class Create {
        private Long accountId;
        private Long amount;
        private Long money;
    }
}
