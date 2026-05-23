package com.diversidade.inclusao.dto;

/**
 * DTO utilizado no cadastro de novas vagas.
 */
public record CreateVagaDTO(String cargo, String flagAfirmativa, Long departamentoId) { }