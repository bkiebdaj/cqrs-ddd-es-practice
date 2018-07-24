package com.github.slimocb.cqrsexample.domain.action;

import com.github.slimocb.cqrsexample.api.EventHandler;
import com.github.slimocb.cqrsexample.api.Gateway;
import com.github.slimocb.cqrsexample.domain.event.UserCreatedEvent;
import com.github.slimocb.cqrsexample.domain.repository.UserRepository;
import com.github.slimocb.cqrsexample.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotifyUserAboutCreatedAccount implements EventHandler<UserCreatedEvent> {

    private final Gateway gateway;
    private final UserRepository userRepository;

    @Override
    public void handle(UserCreatedEvent event) {
        User user = userRepository.findBy(event.getAggregadeId());
        log.info("Send email: {}, account created successfully", user.getEmail());
    }
}
