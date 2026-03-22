package com.biosense.identity.infrastructure.adapter.out.persistence;

import com.biosense.identity.domain.model.Role;
import com.biosense.identity.domain.model.User;
import com.biosense.identity.domain.ports.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final R2dbcEntityTemplate entityTemplate;

    @Override
    public Mono<User> findByUsername(String username) {
        return entityTemplate.selectOne(
                Query.query(Criteria.where("username").is(username)),
                UserEntity.class
        ).flatMap(this::mapToDomain);
    }

    @Override
    public Mono<User> findByEmail(String email) {
        return entityTemplate.selectOne(
                Query.query(Criteria.where("email").is(email)),
                UserEntity.class
        ).flatMap(this::mapToDomain);
    }

    @Override
    public Mono<User> save(User user) {
        UserEntity entity = UserEntity.fromDomain(user);
        return entityTemplate.insert(entity)
                .flatMap(savedEntity -> {
                    user.setId(savedEntity.getId());
                    // Aquí se deberían guardar los roles en la tabla intermedia user_roles
                    // Para simplificar en este paso, asumimos que el guardado fue exitoso
                    return Mono.just(user);
                });
    }

    @Override
    public Mono<Boolean> existsByUsername(String username) {
        return entityTemplate.exists(
                Query.query(Criteria.where("username").is(username)),
                UserEntity.class
        );
    }

    @Override
    public Mono<Boolean> existsByEmail(String email) {
        return entityTemplate.exists(
                Query.query(Criteria.where("email").is(email)),
                UserEntity.class
        );
    }

    private Mono<User> mapToDomain(UserEntity entity) {
        // En una implementación completa, aquí cargaríamos los roles desde user_roles
        return Mono.just(User.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .enabled(entity.getEnabled())
                .build());
    }
}
