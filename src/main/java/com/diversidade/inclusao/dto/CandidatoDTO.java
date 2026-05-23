package com.diversidade.inclusao.dto;

import java.math.BigDecimal;

/**
 * DTO de leitura de candidatos.
 */
public record CandidatoDTO(Long id, String nome, BigDecimal scoreDiversidade, Long vagaId) { }