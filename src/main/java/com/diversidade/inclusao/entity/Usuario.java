package com.diversidade.inclusao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Entidade que representa um usuário autenticável no sistema. Implementa
 * {@link UserDetails} para integração com o Spring Security. Cada usuário
 * possui um nome de usuário único, senha criptografada e um perfil (role)
 * que define suas permissões de acesso.
 */
@Entity
@Table(name = "USUARIOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario implements UserDetails {

    @Id
    @SequenceGenerator(name = "usuario_seq", sequenceName = "USUARIOS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    private Long id;

    /** Nome de usuário para login, deve ser único. */
    @Column(unique = true, nullable = false)
    private String username;

    /** Senha criptografada para autenticação. */
    @Column(nullable = false)
    private String password;

    /** Perfil do usuário que define suas permissões. */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Spring Security usa o prefixo 'ROLE_' para identificar perfis.
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}