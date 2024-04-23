package io.reflectoring.buckpal.stock.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "share")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShareJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long ownerAccountId;

    private Integer quantity;

    private Long stockId;
}
