package com.diversidade.inclusao.dto;

import java.math.BigDecimal;

/**
 * DTO de leitura para departamentos.
 */
public record DepartamentoDTO(Long id, String nome, BigDecimal metaDiversidade) { }