package io.reflectoring.buckpal.stock.adapter.out.persistence;

import javax.persistence.*;

@Entity
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
}
