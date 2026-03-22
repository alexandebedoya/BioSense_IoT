package com.biosense.identity.application.service;

import com.biosense.identity.domain.ports.in.LoginUseCase;
import com.biosense.identity.domain.ports.out.UserRepository;
import com.biosense.identity.infrastructure.adapter.out.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public Mono<String> authenticate(String username, String password) {
        return userRepository.findByUsername(username)
                .flatMap(user -> {
                    if (passwordEncoder.matches(password, user.getPassword())) {
                        return Mono.just(jwtUtil.generateToken(user));
                    } else {
                        return Mono.error(new RuntimeException("Contraseña incorrecta"));
                    }
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Usuario no encontrado")));
    }
}
