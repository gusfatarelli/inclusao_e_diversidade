package com.diversidade.inclusao.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO utilizado para o endpoint de login. Contém o nome de usuário e
 * senha fornecidos pelo cliente.
 */
public record AuthenticationRequest(
        @NotBlank(message = "Usuário não pode ser vazio") String username,
        @NotBlank(message = "Senha não pode ser vazia") String password
) {
}