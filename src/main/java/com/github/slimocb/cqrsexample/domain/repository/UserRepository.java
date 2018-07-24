package com.github.slimocb.cqrsexample.domain.repository;

import com.github.slimocb.cqrsexample.common.AggregadeId;
import com.github.slimocb.cqrsexample.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class UserRepository {
    Map<AggregadeId, User> data = new ConcurrentHashMap<>();

    public void save(User user) {
        log.info("Save user to repository: {}", user);
        data.put(user.getId(), user);
    }

    public User findBy(AggregadeId aggregadeId) {
        return data.get(aggregadeId);
    }

    public boolean exists(String username, String email) {
        return data.values().stream()
                .anyMatch(user -> {
                    return user.getLogin().equals(username) || user.getEmail().equals(email);
                });
    }
}
