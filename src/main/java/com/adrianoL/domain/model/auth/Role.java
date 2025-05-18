package com.adrianoL.domain.model.auth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Role implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Value name;

    @Getter
    public enum Value {
        USER(new SimpleGrantedAuthority("USER")),
        ADMIN(new SimpleGrantedAuthority("ADMIN"));

        private final SimpleGrantedAuthority authority;

        Value(SimpleGrantedAuthority authority) {
            this.authority = authority;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role role)) return false;
        return Objects.equals(getId(), role.getId()) && getName() == role.getName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
