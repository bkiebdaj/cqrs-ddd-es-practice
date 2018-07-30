package org.bkiebdaj.cqrsexample.domain.action;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.api.EventHandler;
import org.bkiebdaj.cqrsexample.core.api.Gateway;
import org.bkiebdaj.cqrsexample.domain.event.UserCreatedEvent;
import org.bkiebdaj.cqrsexample.domain.event.payload.UserCreated;
import org.bkiebdaj.cqrsexample.domain.repository.UserRepository;
import org.bkiebdaj.cqrsexample.domain.user.User;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotifyUserAboutCreatedAccount implements EventHandler<UserCreatedEvent> {

    private final Gateway gateway;
    private final UserRepository userRepository;

    @Override
    public void handle(UserCreatedEvent event) {
        UserCreated userCreated = event.getPayload();
        User user = userRepository.findBy(userCreated.getAggregadeId());
        log.info("Send email: {}, account created successfully", user.getEmail());
    }
}
