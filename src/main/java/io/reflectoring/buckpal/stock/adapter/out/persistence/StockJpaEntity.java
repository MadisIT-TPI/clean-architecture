package io.reflectoring.buckpal.stock.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    @CollectionTable(name = "stock_fluctuation",
            joinColumns = @JoinColumn(name = "stock_id", referencedColumnName = "id"))
    private List<FluctuationValue> fluctuations;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private List<ShareJpaEntity> shares;

    private Long companyId;
}
