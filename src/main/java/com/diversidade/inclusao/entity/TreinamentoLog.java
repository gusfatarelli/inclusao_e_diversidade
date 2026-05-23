package com.diversidade.inclusao.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

/**
 * Entidade que registra a matrícula e conclusão de treinamentos.
 */
@Data
@Entity
@Table(name = "TB_TREINAMENTOS_LOG")
public class TreinamentoLog {
    @Id
    @Column(name = "ID_LOG")
    private Long id;

    @Column(name = "DATA_CONCLUSAO")
    private LocalDate dataConclusao;

    @Column(name = "TIPO_TREINAMENTO", nullable = false)
    private String tipoTreinamento;

    @ManyToOne
    @JoinColumn(name = "FK_COLAB")
    private Colaborador colaborador;
}