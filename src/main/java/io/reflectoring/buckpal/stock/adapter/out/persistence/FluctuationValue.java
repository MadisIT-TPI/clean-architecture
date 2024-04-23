package io.reflectoring.buckpal.stock.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FluctuationValue {

    private Long amount;
    private LocalDateTime timestamp;
}
