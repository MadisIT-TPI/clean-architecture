package io.reflectoring.buckpal.stock.adapter.out.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
class BuyStockJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long stockId;

    @Column(nullable = false)
    private Long money;

    @Column(nullable = false)
    private Long amount;

    public BuyStockJpaEntity(Long stockId, Long money, Long amount) {
        this.stockId = stockId;
        this.money = money;
        this.amount = amount;
    }
}
