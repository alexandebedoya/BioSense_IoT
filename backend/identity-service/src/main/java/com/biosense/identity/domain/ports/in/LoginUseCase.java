package com.biosense.identity.domain.ports.in;

import reactor.core.publisher.Mono;

public interface LoginUseCase {
    Mono<String> authenticate(String username, String password);
}
