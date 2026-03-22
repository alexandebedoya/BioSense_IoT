package com.biosense.identity.application.service;

import com.biosense.identity.domain.model.Role;
import com.biosense.identity.domain.model.User;
import com.biosense.identity.domain.ports.in.LoginWithGoogleUseCase;
import com.biosense.identity.domain.ports.out.GoogleTokenValidator;
import com.biosense.identity.domain.ports.out.UserRepository;
import com.biosense.identity.infrastructure.adapter.out.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoginWithGoogleUseCaseImpl implements LoginWithGoogleUseCase {

    private final GoogleTokenValidator googleTokenValidator;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public Mono<String> login(String idToken) {
        return googleTokenValidator.validate(idToken)
                .flatMap(googleUser -> userRepository.findByEmail(googleUser.getEmail())
                        .switchIfEmpty(registerNewGoogleUser(googleUser))
                        .map(jwtUtil::generateToken)
                );
    }

    private Mono<User> registerNewGoogleUser(com.biosense.identity.domain.model.GoogleUser googleUser) {
        User newUser = User.builder()
                .username(googleUser.getEmail()) // Usamos el email como username inicial
                .email(googleUser.getEmail())
                .password(UUID.randomUUID().toString()) // Password aleatorio, no se usará
                .enabled(true)
                .roles(Collections.singleton(Role.ROLE_USER))
                .build();
        
        return userRepository.save(newUser);
    }
}
