package com.biosense.identity.infrastructure.adapter.out.external;

import com.biosense.identity.domain.model.GoogleUser;
import com.biosense.identity.domain.ports.out.GoogleTokenValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class GoogleTokenValidatorImpl implements GoogleTokenValidator {

    private final WebClient webClient;
    private static final String GOOGLE_TOKEN_INFO_URL = "https://oauth2.googleapis.com/tokeninfo?id_token=";

    @Override
    public Mono<GoogleUser> validate(String idToken) {
        log.info("Validando token de Google...");
        return webClient.get()
                .uri(GOOGLE_TOKEN_INFO_URL + idToken)
                .retrieve()
                .bodyToMono(GoogleUserResponse.class)
                .map(response -> GoogleUser.builder()
                        .email(response.getEmail())
                        .name(response.getName())
                        .picture(response.getPicture())
                        .sub(response.getSub())
                        .build())
                .onErrorResume(e -> Mono.error(new RuntimeException("Token de Google inválido o expirado")));
    }

    @lombok.Data
    public static class GoogleUserResponse {
        private String email;
        private String name;
        private String picture;
        private String sub;
        private String aud; // Client ID
        private String exp; // Expiration
    }
}
