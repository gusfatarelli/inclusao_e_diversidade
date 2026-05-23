package com.diversidade.inclusao.dto;

import java.time.LocalDate;

/**
 * DTO de leitura de logs de treinamento.
 */
public record TreinamentoDTO(Long id, String tipoTreinamento, LocalDate dataConclusao) { }