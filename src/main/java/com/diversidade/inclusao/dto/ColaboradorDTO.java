package com.diversidade.inclusao.dto;

/**
 * DTO de leitura de colaboradores.
 */
public record ColaboradorDTO(Long id, String nome, String genero, String etnia, String pcd,
                              Long departamentoId, String departamentoNome) { }