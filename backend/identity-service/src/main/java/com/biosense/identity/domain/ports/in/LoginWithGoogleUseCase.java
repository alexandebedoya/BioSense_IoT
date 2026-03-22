package com.biosense.identity.domain.ports.in;

import reactor.core.publisher.Mono;

public interface LoginWithGoogleUseCase {
    Mono<String> login(String idToken);
}
