package com.diversidade.inclusao.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

/**
 * Entidade que representa um candidato inscrito em uma vaga.
 */
@Data
@Entity
@Table(name = "TB_CANDIDATOS")
public class Candidato {
    @Id
    @SequenceGenerator(name = "seq_candidato", sequenceName = "SEQ_CANDIDATO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_candidato")
    @Column(name = "ID_CANDIDATO")
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "SCORE_DIVERSIDADE")
    private BigDecimal scoreDiversidade;

    @ManyToOne
    @JoinColumn(name = "FK_VAGA")
    private Vaga vaga;
}