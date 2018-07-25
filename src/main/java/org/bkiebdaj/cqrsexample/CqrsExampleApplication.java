package org.bkiebdaj.cqrsexample;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.common.AggregadeId;
import org.bkiebdaj.cqrsexample.core.event.Event;
import org.bkiebdaj.cqrsexample.core.event.store.EventStore;
import org.bkiebdaj.cqrsexample.core.gateway.SimpleGateway;
import org.bkiebdaj.cqrsexample.domain.command.CreateUserCommand;
import org.bkiebdaj.cqrsexample.domain.command.PayIntoAccountCommand;
import org.bkiebdaj.cqrsexample.domain.command.WithdrawFromAccountCommand;
import org.bkiebdaj.cqrsexample.domain.service.accountpresentation.AccountEntity;
import org.bkiebdaj.cqrsexample.domain.service.accountpresentation.AccountPresentationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
@SpringBootApplication
public class CqrsExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CqrsExampleApplication.class, args);
    }
}

@Slf4j
@RestController
@RequiredArgsConstructor
class SimpleMvc {
    private final SimpleGateway simpleGateway;
    private final EventStore eventStore;
    private final AccountPresentationService accountPresentationService;

    @PostMapping(value = "/createUser", consumes = "application/json")
    public ResponseEntity<String> createUser(@RequestBody CreateUser createUser) {

        log.info("{}", createUser);

        simpleGateway.publishCommand(new CreateUserCommand(createUser.getName(), createUser.getEmail()));

        return ResponseEntity.ok("OK");
    }

    @PostMapping(value = "/payInto", consumes = "application/json")
    public ResponseEntity<String> payInto(@RequestBody AmountChange amountChange) {
        log.info("{}", amountChange);

        simpleGateway.publishCommand(new PayIntoAccountCommand(AggregadeId.of(amountChange.getUserId()), amountChange.getAmount()));

        return ResponseEntity.ok("OK-PAY-INTO");
    }

    @PostMapping(value = "/withdraw", consumes = "application/json")
    public ResponseEntity<String> withdraw(@RequestBody AmountChange amountChange) {
        log.info("{}", amountChange);

        simpleGateway.publishCommand(new WithdrawFromAccountCommand(AggregadeId.of(amountChange.getUserId()), amountChange.getAmount()));

        return ResponseEntity.ok("OK-WITHDRAW");
    }

    @GetMapping(value = "/events", produces = "application/json")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventStore.findAll());
    }

    @GetMapping(value = "/accounts", produces = "application/json")
    public ResponseEntity<List<AccountEntity>> getAllAccounts() {
        return ResponseEntity.ok(accountPresentationService.getAllAccounts());
    }
}

@Data
class CreateUser {
    String name;
    String email;
}

@Data
class AmountChange {
    String userId;
    BigDecimal amount;
}