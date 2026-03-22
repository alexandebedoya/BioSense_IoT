package com.biosense.identity.domain.ports.in;

import com.biosense.identity.domain.model.User;
import reactor.core.publisher.Mono;

public interface RegisterUserUseCase {
    Mono<User> register(User user);
}
