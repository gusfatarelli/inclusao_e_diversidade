package com.diversidade.inclusao.dto;

/**
 * DTO de resposta utilizado nos endpoints de autenticação. Contém o
 * token JWT emitido pelo backend após o sucesso no login ou registro.
 */
public record AuthenticationResponse(String token) {
}