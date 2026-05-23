package com.diversidade.inclusao.dto;

import com.diversidade.inclusao.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO utilizado no fluxo de registro de novos usuários. Contém o nome
 * de usuário, a senha em texto plano (que será codificada no backend) e o
 * perfil (role) que define as permissões do usuário no sistema.
 */
public record RegisterRequest(
        @NotBlank(message = "Usuário não pode ser vazio") String username,
        @NotBlank(message = "Senha não pode ser vazia") String password,
        @NotNull(message = "Perfil é obrigatório") Role role
) {
}