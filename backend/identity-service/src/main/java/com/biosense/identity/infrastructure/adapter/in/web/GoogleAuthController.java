package com.biosense.identity.infrastructure.adapter.in.web;

import com.biosense.identity.domain.ports.in.LoginWithGoogleUseCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth/google")
@RequiredArgsConstructor
public class GoogleAuthController {

    private final LoginWithGoogleUseCase loginWithGoogleUseCase;

    @PostMapping
    public Mono<GoogleLoginResponse> login(@RequestBody GoogleLoginRequest request) {
        return loginWithGoogleUseCase.login(request.getIdToken())
                .map(GoogleLoginResponse::new);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GoogleLoginRequest {
        private String idToken;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GoogleLoginResponse {
        private String token;
    }
}
