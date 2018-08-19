package org.bkiebdaj.cqrsexample.domain.repository;

import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.common.AggregateId;
import org.bkiebdaj.cqrsexample.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class UserRepository {
    private final Map<AggregateId, User> data = new ConcurrentHashMap<>();

    public void save(User user) {
        log.info("Save user to repository: {}", user);
        data.put(user.getId(), user);
    }

    public User findBy(AggregateId aggregateId) {
        return data.get(aggregateId);
    }

    public boolean exists(String username, String email) {
        return data.values().stream()
                .anyMatch(user -> user.getLogin().equals(username) || user.getEmail().equals(email));
    }
}
