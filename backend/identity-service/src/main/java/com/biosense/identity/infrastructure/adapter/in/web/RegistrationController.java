package com.biosense.identity.infrastructure.adapter.in.web;

import com.biosense.identity.domain.model.User;
import com.biosense.identity.domain.ports.in.RegisterUserUseCase;
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
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping("/register")
    public Mono<UserResponse> register(@RequestBody RegistrationRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .build();
        
        return registerUserUseCase.register(user)
                .map(savedUser -> new UserResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail()));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegistrationRequest {
        private String username;
        private String password;
        private String email;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserResponse {
        private Long id;
        private String username;
        private String email;
    }
}
