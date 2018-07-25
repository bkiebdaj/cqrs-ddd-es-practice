package org.bkiebdaj.cqrsexample.domain.service.accountpresentation;

import lombok.Value;
import lombok.experimental.NonFinal;

import java.math.BigDecimal;

@Value
public class AccountEntity {
    String id;
    String number;
    @NonFinal
    BigDecimal amount;

    void increaseAmount(BigDecimal amount) {
        this.amount = this.amount.add(amount);
    }

    void decreaseAmount(BigDecimal amount) {
        this.amount = this.amount.subtract(amount);
    }
}
