package org.bkiebdaj.cqrsexample.domain.repository;

import lombok.extern.slf4j.Slf4j;
import org.bkiebdaj.cqrsexample.core.common.AggregadeId;
import org.bkiebdaj.cqrsexample.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class UserRepository {
    private final Map<AggregadeId, User> data = new ConcurrentHashMap<>();

    public void save(User user) {
        log.info("Save user to repository: {}", user);
        data.put(user.getId(), user);
    }

    public User findBy(AggregadeId aggregadeId) {
        return data.get(aggregadeId);
    }

    public boolean exists(String username, String email) {
        return data.values().stream()
                .anyMatch(user -> user.getLogin().equals(username) || user.getEmail().equals(email));
    }
}
