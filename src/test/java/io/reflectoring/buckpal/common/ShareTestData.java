package io.reflectoring.buckpal.common;

import io.reflectoring.buckpal.account.domain.Account.AccountId;
import io.reflectoring.buckpal.stock.domain.Share;
import io.reflectoring.buckpal.stock.domain.Share.ShareId;

public class ShareTestData {

    public static Share defaultShare() {
        return new ShareBuilder()
                .withId(new ShareId(91L))
                .withOwnerAccountId(new AccountId(342L))
                .withQuantity(100)
                .build();
    }

    public static Share defaultCompanyShare() {
        return new ShareBuilder()
                .withId(new ShareId(1L))
                .withOwnerAccountId(null)
                .withQuantity(1000)
                .build();
    }

    public static class ShareBuilder {

        private ShareId shareId;
        private AccountId ownerAccountId;
        private Integer quantity;

        public ShareBuilder withId(ShareId shareId) {
            this.shareId = shareId;
            return this;
        }

        public ShareBuilder withOwnerAccountId(AccountId ownerAccountId) {
            this.ownerAccountId = ownerAccountId;
            return this;
        }

        public ShareBuilder withQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Share build() {
            return Share.withId(shareId, ownerAccountId, quantity);
        }
    }
}
