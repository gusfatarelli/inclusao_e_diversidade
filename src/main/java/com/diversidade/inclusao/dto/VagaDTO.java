package com.diversidade.inclusao.dto;

/**
 * DTO de leitura de vagas.
 */
public record VagaDTO(Long id, String cargo, String status, String flagAfirmativa, Long departamentoId) { }