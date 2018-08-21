package org.bkiebdaj.cqrsexample.domain.account.aggregate;

import java.util.concurrent.atomic.AtomicInteger;

class AccountNumberGenerator {
    private static AtomicInteger seed = new AtomicInteger(0);

    static String generate() {
        return String.valueOf(seed.addAndGet(4));
    }
}
