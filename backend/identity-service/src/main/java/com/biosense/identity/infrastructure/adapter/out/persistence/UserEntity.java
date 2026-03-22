package com.biosense.identity.infrastructure.adapter.out.persistence;

import com.biosense.identity.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("users")
public class UserEntity {
    @Id
    private Long id;
    private String username;
    private String password;
    private String email;
    private Boolean enabled;

    public static UserEntity fromDomain(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .enabled(user.isEnabled())
                .build();
    }
}
