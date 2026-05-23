package com.diversidade.inclusao.dto;

import java.math.BigDecimal;

/**
 * DTO utilizado no cadastro de novos departamentos.
 */
public record CreateDepartamentoDTO(String nome, BigDecimal metaDiversidade) { }