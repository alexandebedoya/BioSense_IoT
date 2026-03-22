package com.biosense.identity.domain.ports.out;

import com.biosense.identity.domain.model.User;
import reactor.core.publisher.Mono;

public interface UserRepository {
    Mono<User> findByUsername(String username);
    Mono<User> findByEmail(String email);
    Mono<User> save(User user);
    Mono<Boolean> existsByUsername(String username);
    Mono<Boolean> existsByEmail(String email);
}
