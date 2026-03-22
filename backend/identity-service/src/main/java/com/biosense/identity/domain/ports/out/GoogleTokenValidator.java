package com.biosense.identity.domain.ports.out;

import com.biosense.identity.domain.model.GoogleUser;
import reactor.core.publisher.Mono;

public interface GoogleTokenValidator {
    Mono<GoogleUser> validate(String idToken);
}
