package org.bkiebdaj.cqrsexample.domain.action;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.api.EventHandler;
import org.bkiebdaj.cqrsexample.api.Gateway;
import org.bkiebdaj.cqrsexample.domain.event.UserCreated;
import org.bkiebdaj.cqrsexample.domain.repository.UserRepository;
import org.bkiebdaj.cqrsexample.domain.user.User;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotifyUserAboutCreatedAccount implements EventHandler<UserCreated> {

    private final Gateway gateway;
    private final UserRepository userRepository;

    @Override
    public void handle(UserCreated event) {
        User user = userRepository.findBy(event.getAggregadeId());
        log.info("Send email: {}, account created successfully", user.getEmail());
    }
}
