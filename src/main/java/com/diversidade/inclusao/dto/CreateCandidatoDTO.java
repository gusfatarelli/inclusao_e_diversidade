package com.diversidade.inclusao.dto;

import java.math.BigDecimal;

/**
 * DTO utilizado para inscrição de novos candidatos.
 */
public record CreateCandidatoDTO(String nome, BigDecimal scoreDiversidade, Long vagaId) { }