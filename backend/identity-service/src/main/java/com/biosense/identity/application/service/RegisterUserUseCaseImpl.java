package com.biosense.identity.application.service;

import com.biosense.identity.domain.model.Role;
import com.biosense.identity.domain.model.User;
import com.biosense.identity.domain.ports.in.RegisterUserUseCase;
import com.biosense.identity.domain.ports.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<User> register(User user) {
        return userRepository.existsByUsername(user.getUsername())
                .flatMap(exists -> {
                    if (exists) return Mono.error(new RuntimeException("El nombre de usuario ya existe"));
                    return userRepository.existsByEmail(user.getEmail());
                })
                .flatMap(exists -> {
                    if (exists) return Mono.error(new RuntimeException("El email ya existe"));
                    
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    user.setEnabled(true);
                    user.setRoles(Collections.singleton(Role.ROLE_USER));
                    
                    return userRepository.save(user);
                });
    }
}
